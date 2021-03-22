package com.quizhub.property.repositories;

import com.quizhub.property.model.Person;
import com.quizhub.property.model.Quiz;
import com.quizhub.property.model.Score;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ScoreRepository extends CrudRepository<Score, UUID> {
    List<Score> getScoreByPerson(Person p);
    List<Score> getScoreByQuiz(Quiz q);
}
