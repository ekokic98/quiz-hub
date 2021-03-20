package com.quizhub.tournament.repositories;

import com.quizhub.tournament.model.Score;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ScoreRepository extends JpaRepository<Score, UUID> {
}
