package com.quizhub.person.controller;

import com.quizhub.person.exception.BadRequestException;
import com.quizhub.person.exception.ConflictException;
import com.quizhub.person.model.Person;
import com.quizhub.person.service.PersonService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/person-ms/persons")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<Person>> getAllPersons() {
        return ResponseEntity.ok(personService.getAllPersons());
    }

    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(code = 409, message = "Conflict", response = ConflictException.class)
    })
    public ResponseEntity<Person> addPerson(@RequestBody @Valid Person person) {
        return ResponseEntity.ok(personService.addPerson(person));
    }

    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad request", response = BadRequestException.class)
    })
    public ResponseEntity<Person> getPersonById(@RequestParam UUID id) {
        return ResponseEntity.ok(personService.getPersonById(id));
    }

    @GetMapping("/username")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad request", response = BadRequestException.class)
    })
    public ResponseEntity<Person> getPersonByUsername(@RequestParam String username) {
        return ResponseEntity.ok(personService.getPersonByUsername(username));
    }
}
