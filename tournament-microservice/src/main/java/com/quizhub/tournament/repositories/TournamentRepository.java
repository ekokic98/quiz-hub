package com.quizhub.tournament.repositories;

import com.quizhub.tournament.model.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TournamentRepository extends JpaRepository<Tournament, UUID> {
    boolean existsByName(String name);
}
