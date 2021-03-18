package com.quizhub.property.repositories;

import com.quizhub.property.model.Rating;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface RatingRepository  extends CrudRepository<Rating, UUID> {
}
