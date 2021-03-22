package com.quizhub.property.services;

import com.quizhub.property.exceptions.BadRequestException;
import com.quizhub.property.model.Comment;
import com.quizhub.property.repositories.CommentRepository;
import com.quizhub.property.repositories.PersonRepository;
import com.quizhub.property.repositories.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
public class CommentService {
    @Autowired
    private CommentRepository repository;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private QuizRepository quizRepository;

    @Transactional
    public Iterable<Comment> getAllComments () {
        return repository.findAll();
    }

    @Transactional
    public Comment addComment(Comment newComment) {
        if (!(personRepository.existsById(newComment.getPerson().getId()) && quizRepository.existsById(newComment.getQuiz().getId())))
            throw new BadRequestException("Quiz or person does not exist, check provided IDs");
        return repository.save(newComment);
    }

    @Transactional
    public Comment getComment(UUID id) {
        return repository.findById(id).orElseThrow(() -> new BadRequestException("Comment ID is either incorrect or comment does not exist"));
    }

    @Transactional
    public Comment updateComment(Comment comment) {
        if (comment.getId() == null)
            throw new BadRequestException("Id cannot be null");
        Comment existingComment = repository.findById(comment.getId())
                .orElseThrow(() -> new BadRequestException("Comment ID is either incorrect or comment does not exist"));
        existingComment.setContent(comment.getContent());
        return repository.save(existingComment);
    }
}
