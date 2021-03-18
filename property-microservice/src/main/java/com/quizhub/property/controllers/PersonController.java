package com.quizhub.property.controllers;

import com.quizhub.property.model.Person;
import com.quizhub.property.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/person")
public class PersonController {
    @Autowired
    private PersonRepository repository;

    @GetMapping(path="/all")
    public @ResponseBody
    Iterable<Person> getAllPersons () {
        return repository.findAll();
    }
}
