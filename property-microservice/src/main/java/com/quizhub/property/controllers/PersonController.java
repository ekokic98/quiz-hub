package com.quizhub.property.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/property-service/persons")
public class PersonController {
   /* private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping(path="/all")
    public ResponseEntity<Iterable<Person>> getAllPersons () {
        return ResponseEntity.ok(personService.getAllPersons());
    }

    @PostMapping
    @ApiResponses(value = {@ApiResponse(code = 400, message = "Bad request", response = BadRequestException.class),})
    public ResponseEntity<Person> addPerson(@RequestBody @Valid Person person) {
        return ResponseEntity.ok(personService.addPerson(person));
    }

    @GetMapping
    @ApiResponses(value = {@ApiResponse(code = 400, message = "Bad request", response = BadRequestException.class),})
    public ResponseEntity<Person> getPersonById(@RequestParam UUID id) {
        return ResponseEntity.ok(personService.getPersonById(id));
    }

    @GetMapping("/username")
    @ApiResponses(value = {@ApiResponse(code = 400, message = "Bad request", response = BadRequestException.class),})
    public ResponseEntity<Person> getPersonByUsername(@RequestParam String username) {
        return ResponseEntity.ok(personService.getPersonByUsername(username));
    }

    @PutMapping
    @ApiResponses(value = {@ApiResponse(code = 400, message = "Bad request", response = BadRequestException.class),})
    public ResponseEntity<Person> updateComment(@RequestBody @Valid Person person) {
        return ResponseEntity.ok(personService.updatePerson(person));
    }

    @DeleteMapping
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad request", response = BadRequestException.class),
            @ApiResponse(code = 501, message = "Internal server error", response = InternalErrorException.class),})
    public ResponseEntity<JSONObject> deletePersonById (@RequestParam UUID id) {
       return new ResponseEntity<>(personService.deletePersonById(id), HttpStatus.OK);
    }

    @DeleteMapping("/username")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad request", response = BadRequestException.class),
            @ApiResponse(code = 501, message = "Internal server error", response = InternalErrorException.class),})
    public ResponseEntity<JSONObject> deletePersonByUsername (@RequestParam String username) {
        return new ResponseEntity<>(personService.deletePersonByUsername(username), HttpStatus.OK);
    } */
}
