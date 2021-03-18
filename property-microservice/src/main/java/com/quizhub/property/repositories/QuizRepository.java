package com.quizhub.property.repositories;

import com.quizhub.property.model.Quiz;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface QuizRepository  extends CrudRepository<Quiz, UUID> {
}
