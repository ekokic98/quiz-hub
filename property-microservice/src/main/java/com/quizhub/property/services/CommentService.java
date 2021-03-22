package com.quizhub.property.services;

import com.quizhub.property.exceptions.BadRequestException;
import com.quizhub.property.model.Comment;
import com.quizhub.property.repositories.CommentRepository;
import com.quizhub.property.repositories.PersonRepository;
import com.quizhub.property.repositories.QuizRepository;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final PersonRepository personRepository;
    private final QuizRepository quizRepository;

    public CommentService(CommentRepository commentRepository, PersonRepository personRepository, QuizRepository quizRepository) {
        this.commentRepository = commentRepository;
        this.personRepository = personRepository;
        this.quizRepository = quizRepository;
    }

    public Iterable<Comment> getAllComments () {
        return commentRepository.findAll();
    }

    public Comment addComment(Comment newComment) {
        if (!(personRepository.existsById(newComment.getPerson().getId()) && quizRepository.existsById(newComment.getQuiz().getId())))
            throw new BadRequestException("Quiz or person does not exist, check provided IDs");
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
    public JSONObject deleteComment (UUID id) {
        if (!commentRepository.existsById(id)) throw new BadRequestException("Comment with id " + id.toString() + " does not exist");
        commentRepository.deleteById(id);
        if (commentRepository.existsById(id)) throw new BadRequestException("Comment was not deleted (database issue)");
        JSONObject js = new JSONObject(new HashMap<String, String>() {{ put("message", "Comment with id " + id.toString() + " has been successfully deleted");}});
        return js;
    }
}
