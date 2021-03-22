package com.quizhub.property.repositories;


import com.quizhub.property.model.Favorite;
import com.quizhub.property.model.Person;
import com.quizhub.property.model.Quiz;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface FavoriteRepository extends CrudRepository<Favorite, UUID> {
    List<Favorite> getFavoriteByPerson(Person p);
    List<Favorite> getFavoriteByQuiz(Quiz q);
    boolean existsByQuizAndPerson (Quiz q, Person p);
}
