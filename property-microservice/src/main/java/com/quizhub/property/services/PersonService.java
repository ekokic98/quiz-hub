package com.quizhub.property.services;

import com.quizhub.property.exceptions.BadRequestException;
import com.quizhub.property.exceptions.ConflictException;
import com.quizhub.property.exceptions.InternalErrorException;
import com.quizhub.property.model.Person;
import com.quizhub.property.repositories.PersonRepository;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.UUID;

@Service
public class PersonService {
    private final PersonRepository personRepository;

    public PersonService (PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Iterable<Person> getAllPersons() {
        return personRepository.findAll();
    }

    public Person addPerson(Person person) {
        if (personRepository.existsByUsernameIgnoreCase(person.getUsername())) throw new ConflictException("Provided username is already taken");
        return personRepository.save(person);
    }

    public Person getPersonById(UUID id) {
        return personRepository.findById(id).orElseThrow(() -> new BadRequestException("Person with " + id.toString() + " does not exist"));
    }

    public Person getPersonByUsername(String username) {
        return personRepository.findByUsername(username).orElseThrow(() -> new BadRequestException("Person with username " + username + " does not exist"));
    }

    public Person updatePerson(Person person) {
        if (person.getId() == null)
            throw new BadRequestException("Id cannot be null");
        Person existingPerson = personRepository.findById(person.getId())
                .orElseThrow(() -> new BadRequestException("Comment ID is either incorrect or comment does not exist"));
        existingPerson.setUsername(person.getUsername());
        return personRepository.save(existingPerson);
    }

    @Transactional
    public JSONObject deletePersonById (UUID id) {
        if (!personRepository.existsById(id)) throw new BadRequestException("Person with id " + id.toString() + " does not exist");
        personRepository.deleteById(id);
        if (personRepository.existsById(id)) throw new InternalErrorException("Person was not deleted (database issue)");
        JSONObject js = new JSONObject(new HashMap<String, String>() {{ put("message", "Person with id " + id.toString() + " has been successfully deleted");}});
        return js;
    }

    @Transactional
    public JSONObject deletePersonByUsername (String username) {
        if (!personRepository.existsByUsernameIgnoreCase(username)) throw new BadRequestException("Person with id " + username + " does not exist");
        personRepository.deleteByUsernameIgnoreCase(username);
        if (personRepository.existsByUsernameIgnoreCase(username)) throw new InternalErrorException("Person was not deleted (database issue)");
        JSONObject js = new JSONObject(new HashMap<String, String>() {{ put("message", "Person with username " + username + " has been successfully deleted");}});
        return js;
    }
}



