package com.quizhub.tournament.repositories;

import com.quizhub.tournament.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PersonRepository extends JpaRepository<Person, UUID> {

}
