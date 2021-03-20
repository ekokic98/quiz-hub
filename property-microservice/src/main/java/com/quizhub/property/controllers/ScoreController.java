package com.quizhub.property.controllers;

import com.quizhub.property.model.Score;
import com.quizhub.property.repositories.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/score")
public class ScoreController {
    @Autowired
    private ScoreRepository repository;

    @GetMapping(path="/all")
    public @ResponseBody
    Iterable<Score> getAllPersons () {
        return repository.findAll();
    }
}
