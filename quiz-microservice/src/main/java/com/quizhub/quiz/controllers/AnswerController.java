package com.quizhub.quiz.controllers;

import com.quizhub.quiz.exceptions.BadRequestException;
import com.quizhub.quiz.model.Answer;
import com.quizhub.quiz.services.AnswerService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/answers")
public class AnswerController {

    private final AnswerService answerService;

    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad request", response = BadRequestException.class),
    })
    public ResponseEntity<Answer> getAnswer(@RequestParam UUID id) {
        return ResponseEntity.ok(answerService.getAnswer(id));
    }

    @GetMapping("/question")
    public ResponseEntity<List<Answer>> getAnswersByQuestionId(@RequestParam UUID id) {
        return ResponseEntity.ok(answerService.getAnswersByQuestionId(id));
    }

    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad request", response = BadRequestException.class),
    })
    public ResponseEntity<Answer> add(@RequestBody @Valid Answer answer) {
        return ResponseEntity.ok(answerService.add(answer));
    }
}
