package com.quizhub.property.controllers;

import com.quizhub.property.model.Rating;
import com.quizhub.property.repositories.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/rating")
public class RatingController {
    @Autowired
    private RatingRepository repository;

    @GetMapping(path="/all")
    public @ResponseBody
    Iterable<Rating> getAllPersons () {
        return repository.findAll();
    }
}
