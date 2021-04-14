package com.quizhub.property.services;

import com.quizhub.property.dto.Person;
import com.quizhub.property.dto.Quiz;
import com.quizhub.property.exceptions.BadRequestException;
import com.quizhub.property.model.Comment;
import com.quizhub.property.repositories.CommentRepository;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.UUID;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final RestTemplate restTemplate;

    public CommentService(CommentRepository commentRepository, RestTemplate restTemplate) {
        this.commentRepository = commentRepository;
        this.restTemplate = restTemplate;
    }

    public Iterable<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    public Comment addComment(Comment newComment) {
        Quiz quiz = null;
        Person person = null;

        if (newComment.getPerson() == null || newComment.getQuiz() == null) {
            throw new BadRequestException("Quiz or person cannot be null");
        }
        try {
            person = restTemplate.getForObject("http://person-service/api/person-ms/persons?id=" + newComment.getPerson(), Person.class);
            quiz = restTemplate.getForObject("http://quiz-service/api/quiz-ms/quizzes?id=" + newComment.getQuiz(), Quiz.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BadRequestException("Quiz or person does not exist");
        }
        return commentRepository.save(newComment);
    }

    public Comment getComment(UUID id) {
        return commentRepository.findById(id).orElseThrow(() -> new BadRequestException("Comment ID is either incorrect or comment does not exist"));
    }

    public Comment updateComment(Comment comment) {
        if (comment.getId() == null)
            throw new BadRequestException("Id cannot be null");
        Comment existingComment = commentRepository.findById(comment.getId())
                .orElseThrow(() -> new BadRequestException("Comment ID is either incorrect or comment does not exist"));
        existingComment.setContent(comment.getContent());
        return commentRepository.save(existingComment);
    }

    @Transactional
    public JSONObject deleteComment(UUID id) {
        if (!commentRepository.existsById(id))
            throw new BadRequestException("Comment with id " + id + " does not exist");
        commentRepository.deleteById(id);
        if (commentRepository.existsById(id)) throw new BadRequestException("Comment was not deleted (database issue)");
        JSONObject js = new JSONObject(new HashMap<String, String>() {{
            put("message", "Comment with id " + id.toString() + " has been successfully deleted");
        }});
        return js;
    }
}
