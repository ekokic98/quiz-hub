package com.quizhub.property.services;

import com.quizhub.property.exceptions.BadRequestException;
import com.quizhub.property.exceptions.InternalErrorException;
import com.quizhub.property.model.Score;
import com.quizhub.property.repositories.PersonRepository;
import com.quizhub.property.repositories.QuizRepository;
import com.quizhub.property.repositories.ScoreRepository;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.UUID;

@Service
public class ScoreService {
    private final ScoreRepository scoreRepository;
    private final PersonRepository personRepository;
    private final QuizRepository quizRepository;

    public ScoreService (ScoreRepository scoreRepository, PersonRepository personRepository, QuizRepository quizRepository) {
        this.scoreRepository = scoreRepository;
        this.personRepository = personRepository;
        this.quizRepository = quizRepository;
    }

    public Iterable<Score> getAllScores() {
        return scoreRepository.findAll();
    }

    public Iterable<Score> getAllScoresByUser(String username) {
        return scoreRepository.getScoreByPerson(personRepository.findByUsername(username).orElseThrow(() -> new BadRequestException("Person with username " +
                username + " does not exist")));
    }

    public Iterable<Score> getAllScoresByQuiz(UUID id) {
        return scoreRepository.getScoreByQuiz(quizRepository.findById(id).orElseThrow(() -> new BadRequestException("Quiz with id " +
                id.toString() + " does not exist")));
    }

    public Score getScoreById(UUID id) {
        return scoreRepository.findById(id).orElseThrow(() -> new BadRequestException("Score with id " + id.toString() + " does not exist"));
    }

    public Score addScore(Score score) {
        if (!(personRepository.existsById(score.getPerson().getId()) && quizRepository.existsById(score.getQuiz().getId())))
            throw new BadRequestException("Quiz or person does not exist, check provided IDs");
        return scoreRepository.save(score);
    }

    @Transactional
    public JSONObject deleteScore (UUID id) {
        if (!scoreRepository.existsById(id)) throw new BadRequestException("Score with id " + id.toString() + " does not exist");
        scoreRepository.deleteById(id);
        if (scoreRepository.existsById(id)) throw new InternalErrorException("Score was not deleted (database issue)");
        JSONObject js = new JSONObject(new HashMap<String, String>() {{ put("message", "Score with id " + id.toString() + " has been successfully deleted");}});
        return js;
    }
}
