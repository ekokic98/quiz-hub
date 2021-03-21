package com.quizhub.tournament.repositories;

import com.quizhub.tournament.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface QuizRepository extends JpaRepository<Quiz, UUID> {
    List<Quiz> findAllByTournamentId(UUID tournamentId);
}
