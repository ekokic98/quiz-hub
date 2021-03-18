package com.quizhub.property.controllers;

import com.quizhub.property.model.Comment;
import com.quizhub.property.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/comment")
public class CommentController {
    @Autowired
    private CommentRepository repository;

    @GetMapping(path="/all")
    public @ResponseBody
    Iterable<Comment> getAllPersons () {
        return repository.findAll();
    }
}
