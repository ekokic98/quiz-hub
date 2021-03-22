package com.quizhub.property.controllers;

import com.quizhub.property.model.Comment;
import com.quizhub.property.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/comment")
public class CommentController {
    @Autowired
    private CommentService service;

    @GetMapping(path="/all")
    public @ResponseBody
    Iterable<Comment> getAllComments () {
        return service.getAllComments();
    }

    @PostMapping("/add/comment")
    Comment addComment(@RequestBody Comment newComment) {
        return service.addComment(newComment);
    }
}
