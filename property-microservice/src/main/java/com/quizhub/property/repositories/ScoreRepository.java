package com.quizhub.property.repositories;

import com.quizhub.property.model.Score;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ScoreRepository extends CrudRepository<Score, UUID> {
}
