package com.quizhub.quiz.controllers;

import com.quizhub.quiz.exceptions.BadRequestException;
import com.quizhub.quiz.model.Question;
import com.quizhub.quiz.services.QuestionService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/quiz-ms/questions")
public class QuestionController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad request", response = BadRequestException.class),
    })
    public ResponseEntity<Question> getQuestion(@RequestParam UUID id) {
        return ResponseEntity.ok(questionService.getQuestion(id));
    }

    @GetMapping("/quiz")
    public ResponseEntity<List<Question>> getQuestionsByQuizId(@RequestParam UUID id) {
        return ResponseEntity.ok(questionService.getQuestionsByQuizId(id));
    }

    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad request", response = BadRequestException.class),
    })
    public ResponseEntity<Question> add(@RequestBody @Valid Question question) {
        return ResponseEntity.ok(questionService.add(question));
    }
}
