package com.quizhub.property.repositories;


import com.quizhub.property.model.Favorite;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface FavoriteRepository extends CrudRepository<Favorite, UUID> {
    Optional<List<Favorite>> getFavoriteByPerson (UUID id);
    Optional<List<Favorite>> getFavoriteByQuiz(UUID id);
    boolean existsByQuizAndPerson (UUID q, UUID p);
}
