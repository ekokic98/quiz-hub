package com.quizhub.property.services;

import com.quizhub.property.dto.Person;
import com.quizhub.property.dto.Quiz;
import com.quizhub.property.exceptions.BadRequestException;
import com.quizhub.property.exceptions.InternalErrorException;
import com.quizhub.property.model.Score;
import com.quizhub.property.repositories.ScoreRepository;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.UUID;

@Service
public class ScoreService {
    private final ScoreRepository scoreRepository;
    private final RestTemplate restTemplate;

    public ScoreService (ScoreRepository scoreRepository, RestTemplate restTemplate) {
        this.scoreRepository = scoreRepository;
        this.restTemplate = restTemplate;
    }

    public Iterable<Score> getAllScores() {
        return scoreRepository.findAll();
    }

    public Iterable<Score> getAllScoresByUser(String username) {
        Person person;
        try {
            person = restTemplate.getForObject("http://person-service/api/person-ms/persons/username?username=" + username, Person.class);
        } catch (Exception e) {
            throw new BadRequestException("Quiz or person does not exist");
        }
        return scoreRepository.getScoreByPerson(person.getId()).orElseThrow(() -> new BadRequestException("Person with username " +
                username + " does not exist"));
    }


    public Iterable<Score> getAllScoresByQuiz(UUID id) {
        return scoreRepository.getScoreByQuiz(id).orElseThrow(() -> new BadRequestException("Quiz with id " +
                id.toString() + " does not exist"));
    }

    public Score getScoreById(UUID id) {
        return scoreRepository.findById(id).orElseThrow(() -> new BadRequestException("Score with id " + id + " does not exist"));
    }

    public Score addScore(Score score) {
        Quiz quiz;
        Person person;
        if (score.getPerson()==null || score.getQuiz()==null) throw new BadRequestException("Quiz or person cannot be null");
        try {
            quiz = restTemplate.getForObject("http://quiz-service/api/quiz-ms/quizzes?id=" + score.getQuiz(), Quiz.class);
            person = restTemplate.getForObject("http://person-service/api/quiz-ms/person?id=" + score.getPerson(), Person.class);
        }
        catch (Exception e) {
            throw new BadRequestException("Quiz or person does not exist");
        }
        if (quiz!= null && person!= null) throw new BadRequestException("Quiz or person cannot be null");
        return scoreRepository.save(score);
    }

    @Transactional
    public JSONObject deleteScore (UUID id) {
        if (!scoreRepository.existsById(id)) throw new BadRequestException("Score with id " + id + " does not exist");
        scoreRepository.deleteById(id);
        if (scoreRepository.existsById(id)) throw new InternalErrorException("Score was not deleted (database issue)");
        return new JSONObject(new HashMap<String, String>() {{ put("message", "Score with id " + id.toString() + " has been successfully deleted");}});
    }
}
