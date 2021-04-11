package com.quizhub.tournament.services;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.protobuf.Timestamp;
import com.quizhub.tournament.controllers.TournamentController;
import com.quizhub.tournament.dto.Quiz;
import com.quizhub.tournament.event.EventRequest;
import com.quizhub.tournament.event.EventResponse;
import com.quizhub.tournament.event.EventServiceGrpc;
import com.quizhub.tournament.exceptions.BadRequestException;
import com.quizhub.tournament.exceptions.ConflictException;
import com.quizhub.tournament.exceptions.ServiceUnavailableException;
import com.quizhub.tournament.model.Person;
import com.quizhub.tournament.model.Tournament;
import com.quizhub.tournament.repositories.PersonRepository;
import com.quizhub.tournament.repositories.QuizRepository;
import com.quizhub.tournament.repositories.TournamentRepository;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TournamentService {

    private final TournamentRepository tournamentRepository;
    private final PersonRepository personRepository;
    private final RestTemplate restTemplate;
    private final RestTemplate restTemplateBasic;

    private static String externalApiUrl;
    private static String grpcUrl;
    private static int grpcPort;

    @Value("${app.external-api-url}")
    public void setExternalApiUrl(String externalApiUrl) {
        TournamentService.externalApiUrl = externalApiUrl;
    }

    @Value("${app.grpc-url}")
    public void setGrpcUrl(String grpcUrl) {
        TournamentService.grpcUrl = grpcUrl;
    }

    @Value("${app.grpc-port}")
    public void setGrpcPort(int grpcPort) {
        TournamentService.grpcPort = grpcPort;
    }

    public TournamentService(TournamentRepository tournamentRepository, QuizRepository quizRepository, PersonRepository personRepository, @LoadBalanced RestTemplate restTemplate, RestTemplate restTemplateBasic) {
        this.tournamentRepository = tournamentRepository;
        this.personRepository = personRepository;
        this.restTemplate = restTemplate;
        this.restTemplateBasic = restTemplateBasic;
    }

    public Tournament add(Tournament tournament) {
        try {
            if (tournamentRepository.existsByName(tournament.getName())) {
                throw new ConflictException("Name already in use");
            }
            if (tournament.getDateStart().isAfter(tournament.getDateEnd())) {
                throw new BadRequestException("Tournament start date can't be after the end date");
            }
            registerEvent(EventRequest.actionType.CREATE, "/api/tournament-ms/tournaments", "200");
            return tournamentRepository.save(tournament);
        } catch (ConflictException exception) {
            registerEvent(EventRequest.actionType.CREATE, "/api/tournament-ms/tournaments", "409");
            throw exception;
        } catch (BadRequestException exception) {
            registerEvent(EventRequest.actionType.CREATE, "/api/tournament-ms/tournaments", "400");
            throw exception;
        }
    }

    public Tournament update(Tournament tournament) {
        try {
            if (tournament.getId() == null)
                throw new BadRequestException("Tournament id can't be null");
            if (tournament.getDateStart().isAfter(tournament.getDateEnd()))
                throw new BadRequestException("Tournament start date can't be after the end date");
            Tournament existingTournament = tournamentRepository.findById(tournament.getId())
                    .orElseThrow(() -> new BadRequestException("Wrong tournament id"));
            existingTournament.setName(tournament.getName());
            existingTournament.setDateEnd(tournament.getDateEnd());
            registerEvent(EventRequest.actionType.UPDATE, "/api/tournament-ms/tournaments", "200");
            return tournamentRepository.save(existingTournament);
        } catch (BadRequestException exception) {
            registerEvent(EventRequest.actionType.UPDATE, "/api/tournament-ms/tournaments", "400");
            throw exception;
        }
    }

    public List<Tournament> getAllTournaments() {
        registerEvent(EventRequest.actionType.GET, "/api/tournament-ms/tournaments", "200");
        return tournamentRepository.findAll();
    }

    public Tournament getTournament(UUID id) {
        Optional<Tournament> optionalTournament = tournamentRepository.findById(id);
        if (optionalTournament.isPresent()) {
            registerEvent(EventRequest.actionType.GET, "/api/tournament-ms/tournaments", "200");
            return optionalTournament.get();
        } else {
            registerEvent(EventRequest.actionType.GET, "/api/tournament-ms/tournaments", "400");
            throw new BadRequestException("Wrong tournament id");
        }
    }

    public List<Person> getLeaderboardForTournament(UUID id) {
        if (!tournamentRepository.existsById(id)) {
            registerEvent(EventRequest.actionType.GET, "/api/tournament-ms/tournaments/leaderboard", "400");
            throw new BadRequestException("Wrong tournament id");
        }
        registerEvent(EventRequest.actionType.GET, "/api/tournament-ms/tournaments/leaderboard", "200");
        return personRepository.getLeaderboardForTournament(id.toString());
    }

    public Quiz addGeneratedQuizToTournament(TournamentController.QuizParams quizParams) {
        if (!tournamentRepository.existsById(quizParams.getTournamentId())) {
            registerEvent(EventRequest.actionType.CREATE, "/api/tournament-ms/tournaments/quiz", "400");
            throw new BadRequestException("Wrong tournament id");
        }

        QuizApiResponse quizApiResponse = restTemplateBasic.getForObject(
                formApiUrl(quizParams),
                QuizApiResponse.class
        );

        JSONArray questions = new JSONArray();

        for (var question : quizApiResponse.getQuestions()) {
            JSONObject jsonQuestion = new JSONObject();
            jsonQuestion.put("name", question.getQuestion());
            jsonQuestion.put("correctAnswer", question.getCorrectAnswer());

            JSONArray jsonAnswers = new JSONArray();
            Collections.addAll(jsonAnswers, question.getIncorrectAnswers());
            jsonQuestion.put("incorrectAnswers", jsonAnswers);

            questions.add(jsonQuestion);
        }

        JSONObject body = new JSONObject();
        body.put("questions", questions);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(body.toString(), headers);
        try {
            registerEvent(EventRequest.actionType.CREATE, "/api/tournament-ms/tournaments/quiz", "200");
            return restTemplate.postForObject(
                    "http://quiz-service/api/quiz-ms/quizzes/tournament",
                    entity,
                    Quiz.class
            );
        } catch (ResourceAccessException exception) {
            registerEvent(EventRequest.actionType.CREATE, "/api/tournament-ms/tournaments/quiz", "503");
            throw new ServiceUnavailableException("Error while communicating with another microservice.");
        }
    }

    private String formApiUrl(TournamentController.QuizParams quizParams) {
        externalApiUrl += "amount=" + quizParams.getAmount() + "&";
        if (quizParams.getDifficulty() != null) {
            externalApiUrl += "difficulty=" + quizParams.getDifficulty() + "&";
        }
        if (quizParams.getCategory() != null) {
            externalApiUrl += "category=" + quizParams.getCategory() + "&";
        }
        if (quizParams.getType() != null) {
            externalApiUrl += "type=" + quizParams.getType();
        }
        return externalApiUrl;
    }

    private void registerEvent(EventRequest.actionType actionType, String resource, String status) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress(grpcUrl, grpcPort)
                .usePlaintext()
                .build();

        EventServiceGrpc.EventServiceBlockingStub stub = EventServiceGrpc.newBlockingStub(channel);

        Instant time = Instant.now();
        Timestamp timestamp = Timestamp.newBuilder().setSeconds(time.getEpochSecond()).setNanos(time.getNano()).build();

        EventResponse eventResponse = stub.log(EventRequest.newBuilder()
                .setDate(timestamp)
                .setMicroservice("Tournament service")
                .setUser("Unknown")
                .setAction(actionType)
                .setResource(resource)
                .setStatus(status)
                .build());

        System.out.println(eventResponse.getMessage());
        channel.shutdown();
    }

    public static class QuizApiResponse {
        private final int responseCode;
        private final Question[] questions;

        public QuizApiResponse(@JsonProperty("response_code") int responseCode,
                               @JsonProperty("results") Question[] questions) {
            this.responseCode = responseCode;
            this.questions = questions;
        }

        public int getResponseCode() {
            return responseCode;
        }

        public Question[] getQuestions() {
            return questions;
        }
    }

    private static class Question {
        private final String category;
        private final String type;
        private final String difficulty;
        private final String question;
        private final String correctAnswer;
        private final String[] incorrectAnswers;

        public Question(@JsonProperty("category") String category,
                        @JsonProperty("type") String type,
                        @JsonProperty("difficulty") String difficulty,
                        @JsonProperty("question") String question,
                        @JsonProperty("correct_answer") String correctAnswer,
                        @JsonProperty("incorrect_answers") String[] incorrectAnswers) {
            this.category = category;
            this.type = type;
            this.difficulty = difficulty;
            this.question = question;
            this.correctAnswer = correctAnswer;
            this.incorrectAnswers = incorrectAnswers;
        }

        public String getCategory() {
            return category;
        }

        public String getType() {
            return type;
        }

        public String getDifficulty() {
            return difficulty;
        }

        public String getQuestion() {
            return question;
        }

        public String getCorrectAnswer() {
            return correctAnswer;
        }

        public String[] getIncorrectAnswers() {
            return incorrectAnswers;
        }
    }
}
