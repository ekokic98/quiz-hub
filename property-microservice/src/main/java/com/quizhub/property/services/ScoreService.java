package com.quizhub.property.services;

import com.google.common.collect.Iterables;
import com.quizhub.property.dto.Person;
import com.quizhub.property.dto.Quiz;
import com.quizhub.property.event.EventRequest;
import com.quizhub.property.exceptions.BadRequestException;
import com.quizhub.property.exceptions.ConflictException;
import com.quizhub.property.exceptions.InternalErrorException;
import com.quizhub.property.model.Score;
import com.quizhub.property.repositories.ScoreRepository;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.UUID;

import static com.quizhub.property.services.PropertyService.registerEvent;

@Service
public class ScoreService {
    private final ScoreRepository scoreRepository;
    private final RestTemplate restTemplate;

    public ScoreService(ScoreRepository scoreRepository, RestTemplate restTemplate) {
        this.scoreRepository = scoreRepository;
        this.restTemplate = restTemplate;
    }

    public Iterable<Score> getAllScores() {
        registerEvent(EventRequest.actionType.GET, "/api/property-ms/scores/all", "200");
        return scoreRepository.findAll();
    }

    public Score getScoreById(UUID id) {
        registerEvent(EventRequest.actionType.GET, "/api/property-ms/scores", "200");
        return scoreRepository.findById(id)
                .orElseThrow(() -> {
                    registerEvent(EventRequest.actionType.GET, "/api/property-ms/scores", "400");
                    return new BadRequestException("Score with id " + id + " does not exist");
                });
    }

    public Iterable<Score> getAllScoresByUser(String username) {
        Person person;
        try {
            person = restTemplate.getForObject("http://person-service/api/person-ms/persons/username?username=" + username, Person.class);
        } catch (Exception e) {
            throw new BadRequestException("Quiz or person does not exist");
        }
        registerEvent(EventRequest.actionType.GET, "/api/property-ms/scores/all/user", "200");
        Iterable<Score> t = scoreRepository.getScoresByPerson(person.getId())
                .orElseThrow(() -> {
                    registerEvent(EventRequest.actionType.GET, "/api/property-ms/scores/all/user", "400");
                    return new BadRequestException("Person with username " + username + " does not exist");
                });
        if (Iterables.size(t) == 0) {
            throw new BadRequestException("Quiz or person does not exist");
        }
        return t;
    }


    public Iterable<Score> getAllScoresByQuiz(UUID id) {
        registerEvent(EventRequest.actionType.GET, "/api/property-ms/scores/all/user", "200");
        return scoreRepository.getScoresByQuiz(id)
                .orElseThrow(() -> {
                    registerEvent(EventRequest.actionType.GET, "/api/property-ms/scores/all/user", "400");
                    return new BadRequestException("Person with username " + id + " does not exist");
                });
    }

    public Score addScore(Score score) {
        try {
            Quiz quiz;
            Person person;
            if (score.getPerson() == null || score.getQuiz() == null) {
                throw new BadRequestException("Quiz or person cannot be null");
            }
            try {
                person = restTemplate.getForObject("http://person-service/api/person-ms/persons?id=" + score.getPerson(), Person.class);
                quiz = restTemplate.getForObject("http://quiz-service/api/quiz-ms/quizzes?id=" + score.getQuiz(), Quiz.class);
            } catch (Exception e) {
                throw new BadRequestException("Quiz or person does not exist");
            }
            if (quiz == null || person == null) {
                throw new BadRequestException("Quiz or person cannot be null");
            }
            registerEvent(EventRequest.actionType.CREATE, "/api/property-ms/scores", "200");
            return scoreRepository.save(score);
        } catch (ConflictException exception) {
            registerEvent(EventRequest.actionType.CREATE, "/api/property-ms/scores", "409");
            throw exception;
        } catch (BadRequestException exception) {
            registerEvent(EventRequest.actionType.CREATE, "/api/property-ms/scores", "400");
            throw exception;
        }
    }

    @Transactional
    public JSONObject deleteScore(UUID id) {
        try {
            if (!scoreRepository.existsById(id)) {
                throw new BadRequestException("Score with id " + id + " does not exist");
            }
            scoreRepository.deleteById(id);
            if (scoreRepository.existsById(id)) {
                throw new InternalErrorException("Score was not deleted (database issue)");
            }
            registerEvent(EventRequest.actionType.DELETE, "/api/property-ms/scores", "200");
            return new JSONObject(new HashMap<String, String>() {{
                put("message", "Score with id " + id.toString() + " has been successfully deleted");
            }});
        } catch (BadRequestException exception) {
            registerEvent(EventRequest.actionType.DELETE, "/api/property-ms/scores", "400");
            throw exception;
        }
    }
}
