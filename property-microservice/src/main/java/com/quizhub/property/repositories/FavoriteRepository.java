package com.quizhub.property.repositories;


import com.quizhub.property.model.Favorite;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface FavoriteRepository extends CrudRepository<Favorite, UUID> {
}
