package com.quizhub.quiz.services;

import com.google.protobuf.Timestamp;
import com.quizhub.quiz.controllers.QuizController;
import com.quizhub.quiz.dto.Person;
import com.quizhub.quiz.event.EventRequest;
import com.quizhub.quiz.event.EventResponse;
import com.quizhub.quiz.event.EventServiceGrpc;
import com.quizhub.quiz.exceptions.BadRequestException;
import com.quizhub.quiz.exceptions.ConflictException;
import com.quizhub.quiz.exceptions.ServiceUnavailableException;
import com.quizhub.quiz.model.Answer;
import com.quizhub.quiz.model.Category;
import com.quizhub.quiz.model.Question;
import com.quizhub.quiz.model.Quiz;
import com.quizhub.quiz.model.enums.QuestionType;
import com.quizhub.quiz.repositories.AnswerRepository;
import com.quizhub.quiz.repositories.CategoryRepository;
import com.quizhub.quiz.repositories.QuestionRepository;
import com.quizhub.quiz.repositories.QuizRepository;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class QuizService {

    private final QuizRepository quizRepository;
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final CategoryRepository categoryRepository;
    private final RestTemplate restTemplate;

    private static String grpcUrl;
    private static int grpcPort;

    @Value("${app.grpc-url}")
    public void setGrpcUrl(String grpcUrl) {
        QuizService.grpcUrl = grpcUrl;
    }

    @Value("${app.grpc-port}")
    public void setGrpcPort(int grpcPort) {
        QuizService.grpcPort = grpcPort;
    }

    public QuizService(QuizRepository quizRepository, QuestionRepository questionRepository, AnswerRepository answerRepository, CategoryRepository categoryRepository, RestTemplate restTemplate) {
        this.quizRepository = quizRepository;
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
        this.categoryRepository = categoryRepository;
        this.restTemplate = restTemplate;
    }

    public List<Quiz> getAllQuizzes(Boolean tournament) {
        registerEvent(EventRequest.actionType.GET, "/api/quizzes/all", "200");
        if (tournament == null) {
            return quizRepository.findAll();
        }
        if (tournament) {
            return quizRepository.findAllByTournamentIdIsNotNull();
        } else {
            return quizRepository.findAllByTournamentIdIsNull();
        }
    }

    public Quiz add(Quiz quiz) {
        Person person;

        try {
            person = restTemplate.getForObject(
                "http://person-service/api/persons?id=" + quiz.getPersonId().toString(),
                Person.class
            );
        } catch (ResourceAccessException exception) {
            registerEvent(EventRequest.actionType.CREATE, "/api/quizzes", "503");
            throw new ServiceUnavailableException("Error while communicating with another microservice.");
        }

        if (quizRepository.existsByName(quiz.getName())) {
            registerEvent(EventRequest.actionType.CREATE, "/api/quizzes", "409");
            throw new ConflictException("Name already in use");
        }

        Optional<Category> savedCategory = categoryRepository.findById(quiz.getCategory().getId());

        Category quizCategory;

        if (savedCategory.isEmpty()) {
            if (categoryRepository.existsByName(quiz.getCategory().getName())) {
                registerEvent(EventRequest.actionType.CREATE, "/api/quizzes", "400");
                throw new BadRequestException("Category name already exists.");
            }
            quizCategory = categoryRepository.save(quiz.getCategory());
        } else {
            quizCategory = savedCategory.get();
        }

        registerEvent(EventRequest.actionType.CREATE, "/api/quizzes", "200");
        return quizRepository.save(new Quiz(
                quiz.getId(),
                person.getId(),
                quizCategory,
                quiz.getName(),
                null,
                quiz.getDateCreated(),
                quiz.getTimeLimit(),
                quiz.getTotalQuestions()
        ));
    }

    public Quiz getQuiz(UUID id) {
        Optional<Quiz> optionalQuiz = quizRepository.findById(id);
        if (optionalQuiz.isPresent()) {
            registerEvent(EventRequest.actionType.GET, "/api/quizzes", "200");
            return optionalQuiz.get();
        } else {
            registerEvent(EventRequest.actionType.GET, "/api/quizzes", "400");
            throw new BadRequestException("Wrong quiz id");
        }
    }

    public List<Quiz> getQuizzesByCategory(UUID id) {
        registerEvent(EventRequest.actionType.GET, "/api/quizzes/category", "200");
        return quizRepository.findAllByCategoryId(id);
    }

    public List<Quiz> getQuizzesByName(String name) {
        registerEvent(EventRequest.actionType.GET, "/api/quizzes/search", "200");
        return quizRepository.getQuizzesByName(name);
    }

    public Quiz getRandomQuiz() {
        Optional<Quiz> optionalQuiz = quizRepository.getRandomQuiz();
        if (optionalQuiz.isPresent()) {
            registerEvent(EventRequest.actionType.GET, "/api/quizzes/random", "200");
            return optionalQuiz.get();
        } else {
            registerEvent(EventRequest.actionType.GET, "/api/quizzes/random", "400");
            throw new BadRequestException("No active quizzes in database");
        }
    }

    @Transactional
    public JSONObject deleteQuizById(UUID id) {
        if (!quizRepository.existsById(id)) {
            registerEvent(EventRequest.actionType.DELETE, "/api/quizzes", "400");
            throw new BadRequestException("Quiz with id " + id.toString() + " does not exist");
        }
        registerEvent(EventRequest.actionType.DELETE, "/api/quizzes", "200");
        quizRepository.deleteById(id);
        return new JSONObject(new HashMap<String, String>() {{
            put("message", "Quiz with id " + id.toString() + " has been successfully deleted");
        }});
    }

    public Quiz addQuizFromTournament(QuizController.TournamentQuiz tournamentQuiz) {
        Quiz quiz = new Quiz();
        quiz.setName(LocalDateTime.now().toString());
        quiz.setTimeLimit(tournamentQuiz.getQuestionsLength() * 15);
        quiz.setTotalQuestions(tournamentQuiz.getQuestionsLength());
        Quiz savedQuiz = quizRepository.save(quiz);
        tournamentQuiz.getQuestions();
        for (var question : tournamentQuiz.getQuestions()) {
            Question savedQuestion = questionRepository.save(new Question(savedQuiz, question.getName(), QuestionType.SINGLE_CHOICE));
            answerRepository.save(new Answer(savedQuestion, question.getCorrectAnswer(), true));
            for (var answer : question.getIncorrectAnswers()) {
                answerRepository.save(new Answer(savedQuestion, answer, false));
            }
        }
        registerEvent(EventRequest.actionType.CREATE, "/api/quizzes/tournament", "200");
        return savedQuiz;
    }

    public List<Quiz> getQuizzesForTournament(UUID id) {
        registerEvent(EventRequest.actionType.GET, "/api/quizzes/tournament", "200");
        return quizRepository.findAllByTournamentId(id);
    }

    public static void registerEvent(EventRequest.actionType actionType, String resource, String status) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress(grpcUrl, grpcPort)
                .usePlaintext()
                .build();

        EventServiceGrpc.EventServiceBlockingStub stub = EventServiceGrpc.newBlockingStub(channel);

        Instant time = Instant.now();
        Timestamp timestamp = Timestamp.newBuilder().setSeconds(time.getEpochSecond()).setNanos(time.getNano()).build();

        try {
            EventResponse eventResponse = stub.log(EventRequest.newBuilder()
                    .setDate(timestamp)
                    .setMicroservice("Quiz service")
                    .setUser("Unknown")
                    .setAction(actionType)
                    .setResource(resource)
                    .setStatus(status)
                    .build());

            System.out.println(eventResponse.getMessage());
        } catch (StatusRuntimeException e) {
            System.out.println("System event microservice not running");
        }

        channel.shutdown();
    }


}
