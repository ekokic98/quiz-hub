package com.quizhub.property.services;

import com.quizhub.property.dto.Person;
import com.quizhub.property.dto.Quiz;
import com.quizhub.property.event.EventRequest;
import com.quizhub.property.exceptions.BadRequestException;
import com.quizhub.property.exceptions.ConflictException;
import com.quizhub.property.model.Comment;
import com.quizhub.property.repositories.CommentRepository;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.UUID;

import static com.quizhub.property.services.PropertyService.registerEvent;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final RestTemplate restTemplate;

    public CommentService(CommentRepository commentRepository, RestTemplate restTemplate) {
        this.commentRepository = commentRepository;
        this.restTemplate = restTemplate;
    }

    public Iterable<Comment> getAllComments() {
        registerEvent(EventRequest.actionType.GET, "/api/comments/all", "200");
        return commentRepository.findAll();
    }

    public Comment getComment(UUID id) {
        registerEvent(EventRequest.actionType.GET, "/api/comments", "200");
        return commentRepository.findById(id)
                .orElseThrow(() -> {
                    registerEvent(EventRequest.actionType.GET, "/api/comments", "400");
                    return new BadRequestException("Comment ID is either incorrect or comment does not exist.");
                });
    }

    public Comment addComment(Comment newComment) {
        try {
            Quiz quiz = null;
            Person person = null;
            if (newComment.getPerson() == null || newComment.getQuiz() == null) {
                throw new BadRequestException("Quiz or person cannot be null");
            }
            try {
                person = restTemplate.getForObject("http://person-service/api/persons?id=" + newComment.getPerson(), Person.class);
                quiz = restTemplate.getForObject("http://quiz-service/api/quizzes?id=" + newComment.getQuiz(), Quiz.class);
            } catch (Exception e) {
                throw new BadRequestException("Quiz or person does not exist");
            }
            registerEvent(EventRequest.actionType.CREATE, "/api/comments", "200");
            return commentRepository.save(newComment);
        } catch (ConflictException exception) {
            registerEvent(EventRequest.actionType.CREATE, "/api/comments", "409");
            throw exception;
        } catch (BadRequestException exception) {
            registerEvent(EventRequest.actionType.CREATE, "/api/comments", "400");
            throw exception;
        }
    }

    public Comment updateComment(Comment comment) {
        try {
            if (comment.getId() == null) {
                throw new BadRequestException("Id cannot be null");
            }
            Comment existingComment = commentRepository.findById(comment.getId())
                    .orElseThrow(() -> new BadRequestException("Comment ID is either incorrect or comment does not exist"));
            existingComment.setContent(comment.getContent());
            registerEvent(EventRequest.actionType.UPDATE, "/api/comments", "200");
            return commentRepository.save(existingComment);
        } catch (BadRequestException exception) {
            registerEvent(EventRequest.actionType.UPDATE, "/api/comments", "400");
            throw exception;
        }
    }

    @Transactional
    public JSONObject deleteComment(UUID id) {
        try {
            if (!commentRepository.existsById(id)) {
                throw new BadRequestException("Comment with id " + id + " does not exist");
            }
            commentRepository.deleteById(id);
            if (commentRepository.existsById(id)) {
                throw new BadRequestException("Comment was not deleted (database issue)");
            }
            registerEvent(EventRequest.actionType.DELETE, "/api/comments", "200");
            return new JSONObject(new HashMap<String, String>() {{
                put("message", "Comment with id " + id.toString() + " has been successfully deleted");
            }});
        } catch (BadRequestException exception) {
            registerEvent(EventRequest.actionType.DELETE, "/api/comments", "400");
            throw exception;
        }
    }
}
