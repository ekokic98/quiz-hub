package com.quizhub.property.repositories;


import com.quizhub.property.model.Favorite;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FavoriteRepository extends CrudRepository<Favorite, UUID> {
}
