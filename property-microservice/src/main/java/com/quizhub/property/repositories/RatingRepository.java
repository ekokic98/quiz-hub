package com.quizhub.property.repositories;


import com.quizhub.property.model.Rating;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RatingRepository  extends CrudRepository<Rating, UUID> {
    Optional<List<Rating>> getRatingByQuiz(UUID q);
    Optional<List<Rating>> getRatingByPerson(UUID u);
    boolean existsByQuizAndPerson (UUID q, UUID p);
}
