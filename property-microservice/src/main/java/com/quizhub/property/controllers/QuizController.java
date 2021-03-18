package com.quizhub.property.controllers;

import com.quizhub.property.model.Quiz;
import com.quizhub.property.repositories.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/quiz")
public class QuizController {
    @Autowired
    private QuizRepository repository;

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Quiz> getAllPersons () {
        return repository.findAll();
    }

}
