package com.quizhub.property.services;

import com.quizhub.property.model.Comment;
import com.quizhub.property.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CommentService {
    @Autowired
    private CommentRepository repository;

    @Transactional
    public Iterable<Comment> getAllComments () {
        return repository.findAll();
    }

    @Transactional
    public Comment addComment(Comment newComment) {
        return repository.save(newComment);
    }
}
