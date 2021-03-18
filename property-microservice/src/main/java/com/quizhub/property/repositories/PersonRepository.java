package com.quizhub.property.repositories;

import com.quizhub.property.model.Person;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface PersonRepository extends CrudRepository<Person, UUID> {
    Person findByUsername(String username);
    boolean existsByUsername(String username);
}
