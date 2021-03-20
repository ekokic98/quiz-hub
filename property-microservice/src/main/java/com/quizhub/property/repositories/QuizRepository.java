package com.quizhub.property.repositories;

import com.quizhub.property.model.Quiz;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface QuizRepository  extends CrudRepository<Quiz, UUID> {
}
