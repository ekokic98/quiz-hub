package com.quizhub.tournament.services;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.quizhub.tournament.controllers.TournamentController;
import com.quizhub.tournament.dto.Quiz;
import com.quizhub.tournament.exceptions.BadRequestException;
import com.quizhub.tournament.exceptions.ConflictException;
import com.quizhub.tournament.exceptions.ServiceUnavailableException;
import com.quizhub.tournament.model.Person;
import com.quizhub.tournament.model.Tournament;
import com.quizhub.tournament.repositories.PersonRepository;
import com.quizhub.tournament.repositories.QuizRepository;
import com.quizhub.tournament.repositories.TournamentRepository;
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

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class TournamentService {

    private final TournamentRepository tournamentRepository;
    private final QuizRepository quizRepository;
    private final PersonRepository personRepository;
    private final RestTemplate restTemplate;
    private final RestTemplate restTemplateBasic;

    private static String externalApiUrl;

    @Value("${app.external-api-url}")
    public void setExternalApiUrl(String externalApiUrl) {
        TournamentService.externalApiUrl = externalApiUrl;
    }

    public TournamentService(TournamentRepository tournamentRepository, QuizRepository quizRepository, PersonRepository personRepository, @LoadBalanced RestTemplate restTemplate, RestTemplate restTemplateBasic) {
        this.tournamentRepository = tournamentRepository;
        this.quizRepository = quizRepository;
        this.personRepository = personRepository;
        this.restTemplate = restTemplate;
        this.restTemplateBasic = restTemplateBasic;
    }

    public Tournament add(Tournament tournament) {
        if (tournamentRepository.existsByName(tournament.getName()))
            throw new ConflictException("Name already in use");
        if (tournament.getDateStart().isAfter(tournament.getDateEnd()))
            throw new BadRequestException("Tournament start date can't be after the end date");
        return tournamentRepository.save(tournament);
    }

    public Tournament update(Tournament tournament) {
        if (tournament.getId() == null)
            throw new BadRequestException("Tournament id can't be null");
        if (tournament.getDateStart().isAfter(tournament.getDateEnd()))
            throw new BadRequestException("Tournament start date can't be after the end date");
        Tournament existingTournament = tournamentRepository.findById(tournament.getId())
                .orElseThrow(() -> new BadRequestException("Wrong tournament id"));
        existingTournament.setName(tournament.getName());
        existingTournament.setDateEnd(tournament.getDateEnd());
        return tournamentRepository.save(existingTournament);
    }

    public List<Tournament> getAllTournaments() {
        return tournamentRepository.findAll();
    }

    public Tournament getTournament(UUID id) {
        return tournamentRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Wrong tournament id"));
    }

    public List<Person> getLeaderboardForTournament(UUID id) {
        if (!tournamentRepository.existsById(id)) {
            throw new BadRequestException("Wrong tournament id");
        }
        return personRepository.getLeaderboardForTournament(id.toString());
    }

    public Quiz addGeneratedQuizToTournament(TournamentController.QuizParams quizParams) {
        if (!tournamentRepository.existsById(quizParams.getTournamentId())) {
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
            return restTemplate.postForObject(
                    "http://quiz-service/api/quiz-ms/quizzes/tournament",
                    entity,
                    Quiz.class
            );
        } catch (ResourceAccessException exception) {
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
