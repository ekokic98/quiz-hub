package com.quizhub.property.repositories;

import com.quizhub.property.model.Score;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ScoreRepository extends CrudRepository<Score, UUID> {
    Optional<List<Score>> getScoresByPerson(UUID p);
    List<Score> getScoresByQuizOrderByPointsDescDateScoredAsc(UUID q);

    Iterable<Score> findByDateScoredBetweenOrderByDateScoredDesc(LocalDateTime from, LocalDateTime to);

    Optional<Score> findFirstByQuizAndPersonOrderByPointsDescDateScoredAsc(UUID quiz, UUID person);
}
