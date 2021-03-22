package com.quizhub.property.controllers;

import com.quizhub.property.exceptions.BadRequestException;
import com.quizhub.property.model.Comment;
import com.quizhub.property.services.CommentService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/property-service/comments")
public class CommentController {
    @Autowired
    private CommentService service;

    @GetMapping(path="/all")
    public @ResponseBody
    ResponseEntity<Iterable<Comment>> getAllComments () {
        return ResponseEntity.ok(service.getAllComments());
    }

    @PostMapping
    @ApiResponses(value = {@ApiResponse(code = 400, message = "Bad request", response = BadRequestException.class),})
    public ResponseEntity<Comment> addComment(@RequestBody @Valid Comment newComment) {
        return ResponseEntity.ok(service.addComment(newComment));
    }

    @GetMapping
    @ApiResponses(value = {@ApiResponse(code = 400, message = "Bad request", response = BadRequestException.class),})
    public ResponseEntity<Comment> getComment(@RequestParam UUID id) {
        return ResponseEntity.ok(service.getComment(id));
    }

    @PutMapping
    @ApiResponses(value = {@ApiResponse(code = 400, message = "Bad request", response = BadRequestException.class),})
    public ResponseEntity<Comment> updateComment(@RequestBody @Valid Comment comment) {
        return ResponseEntity.ok(service.updateComment(comment));
    }

}
