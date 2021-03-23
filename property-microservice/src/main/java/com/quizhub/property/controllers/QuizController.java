package com.quizhub.property.controllers;

import com.quizhub.property.exceptions.BadRequestException;
import com.quizhub.property.exceptions.InternalErrorException;
import com.quizhub.property.model.Quiz;
import com.quizhub.property.services.QuizService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/property-service/quizzes")
public class QuizController {
    final private QuizService quizService;

    public QuizController (QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping(path="/all")
    public ResponseEntity<Iterable<Quiz>> getAllQuizzes () {
        return ResponseEntity.ok(quizService.getAllQuizzes());
    }

    @PostMapping
    @ApiResponses(value = {@ApiResponse(code = 400, message = "Bad request", response = BadRequestException.class),})
    public ResponseEntity<Quiz> addQuiz(@RequestBody @Valid Quiz quiz) {
        return ResponseEntity.ok(quizService.addQuiz(quiz));
    }

    @GetMapping
    @ApiResponses(value = {@ApiResponse(code = 400, message = "Bad request", response = BadRequestException.class),})
    public ResponseEntity<Quiz> getQuizById(@RequestParam UUID id) {
        return ResponseEntity.ok(quizService.getQuizById(id));
    }

    @GetMapping("/all/user")
    public ResponseEntity<Iterable<Quiz>> getAllQuizzesByUser (@RequestParam String username) {
        return ResponseEntity.ok(quizService.getAllQuizzesByUser(username));
    }

    @DeleteMapping
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad request", response = BadRequestException.class),
            @ApiResponse(code = 501, message = "Internal server error", response = InternalErrorException.class),})
    public ResponseEntity<JSONObject> deleteQuizById (@RequestParam UUID id) {
        return new ResponseEntity<>(quizService.deleteQuizById(id), HttpStatus.OK);
    }

    @PutMapping
    @ApiResponses(value = {@ApiResponse(code = 400, message = "Bad request", response = BadRequestException.class),})
    public ResponseEntity<Quiz> updateComment(@RequestBody @Valid Quiz person) {
        return ResponseEntity.ok(quizService.updateQuiz(person));
    }
}

