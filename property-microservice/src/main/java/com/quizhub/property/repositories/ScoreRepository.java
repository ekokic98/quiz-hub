package com.quizhub.property.repositories;

import com.quizhub.property.model.Score;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ScoreRepository extends CrudRepository<Score, UUID> {
    List<Score> getScoreByPerson(UUID p);
    Optional<List<Score>> getScoreByQuiz(UUID q);
}
