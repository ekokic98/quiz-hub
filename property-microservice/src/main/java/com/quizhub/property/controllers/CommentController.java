package com.quizhub.property.controllers;

import com.quizhub.property.exceptions.BadRequestException;
import com.quizhub.property.exceptions.InternalErrorException;
import com.quizhub.property.model.Comment;
import com.quizhub.property.responses.CommentResponse;
import com.quizhub.property.services.CommentService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping(path="/all")
    public @ResponseBody
    ResponseEntity<Iterable<Comment>> getAllComments () {
        return ResponseEntity.ok(commentService.getAllComments());
    }

    @GetMapping("/quiz")
    @ApiResponses(value = {@ApiResponse(code = 400, message = "Bad request", response = BadRequestException.class)})
    public ResponseEntity<List<CommentResponse>> getCommentsByQuiz(@RequestParam UUID quizId) {
        return ResponseEntity.ok(commentService.getCommentsByQuiz(quizId));
    }

    @PostMapping
    @ApiResponses(value = {@ApiResponse(code = 400, message = "Bad request", response = BadRequestException.class),})
    public ResponseEntity<Comment> addComment(@RequestBody @Valid Comment newComment) {
        return ResponseEntity.ok(commentService.addComment(newComment));
    }

    @GetMapping
    @ApiResponses(value = {@ApiResponse(code = 400, message = "Bad request", response = BadRequestException.class),})
    public ResponseEntity<Comment> getComment(@RequestParam UUID id) {
        return ResponseEntity.ok(commentService.getComment(id));
    }

    @PutMapping
    @ApiResponses(value = {@ApiResponse(code = 400, message = "Bad request", response = BadRequestException.class),})
    public ResponseEntity<Comment> updateComment(@RequestBody @Valid Comment comment) {
        return ResponseEntity.ok(commentService.updateComment(comment));
    }

    @DeleteMapping
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad request", response = BadRequestException.class),
            @ApiResponse(code = 501, message = "Internal server error", response = InternalErrorException.class),})
    public ResponseEntity<JSONObject> deleteComment(@RequestParam UUID id) {
        return new ResponseEntity<>(commentService.deleteComment(id), HttpStatus.OK);
    }

}
