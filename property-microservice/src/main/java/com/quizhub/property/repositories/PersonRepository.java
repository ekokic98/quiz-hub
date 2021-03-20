package com.quizhub.property.repositories;

import com.quizhub.property.model.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PersonRepository extends CrudRepository<Person, UUID> {
    Person findByUsername(String username);
    boolean existsByUsername(String username);
}