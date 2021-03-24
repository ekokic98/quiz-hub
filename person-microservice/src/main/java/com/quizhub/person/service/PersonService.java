package com.quizhub.person.service;

import com.quizhub.person.exception.BadRequestException;
import com.quizhub.person.exception.ConflictException;
import com.quizhub.person.model.Person;
import com.quizhub.person.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    public Person addPerson(Person person) {
        if (personRepository.existsByUsernameIgnoreCase(person.getUsername())) {
            throw new ConflictException("Username already taken.");
        }
        if (personRepository.existsByEmail(person.getUsername())) {
            throw new ConflictException("Email already taken.");
        }
        return personRepository.save(person);
    }

    public Person getPersonById(UUID id) {
        return personRepository.findById(id).orElseThrow(() ->
                new BadRequestException("Person with id " + id.toString() + " does not exist."));
    }

    public Person getPersonByUsername(String username) {
        return personRepository.findByUsername(username).orElseThrow(() ->
                new BadRequestException("Person with username " + username + " does not exist."));
    }
}
