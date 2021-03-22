package com.quizhub.property.repositories;


import com.quizhub.property.model.Person;
import com.quizhub.property.model.Quiz;
import com.quizhub.property.model.Rating;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RatingRepository  extends CrudRepository<Rating, UUID> {
    List<Rating> getRatingByPerson(Person p);
    List<Rating> getRatingByQuiz(Quiz q);
    boolean existsByQuizAndPerson (Quiz q, Person p);
}
