package com.quizhub.property.controllers;

import com.quizhub.property.model.Favorite;
import com.quizhub.property.repositories.FavoriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/favorite")
public class FavoriteController {
    @Autowired
    private FavoriteRepository repository;

    @GetMapping(path="/all")
    public @ResponseBody
    Iterable<Favorite> getAllPersons () {
        return repository.findAll();
    }
}
