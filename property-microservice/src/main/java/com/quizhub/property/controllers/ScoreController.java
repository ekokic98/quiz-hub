package com.quizhub.property.controllers;

import com.quizhub.property.exceptions.BadRequestException;
import com.quizhub.property.exceptions.InternalErrorException;
import com.quizhub.property.model.Score;
import com.quizhub.property.services.ScoreService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping(path="/api/property-ms/scores")
public class ScoreController {
    private final ScoreService scoreService;

    public ScoreController(ScoreService scoreService) {
        this.scoreService = scoreService;
    }

    @GetMapping("/all")
    public ResponseEntity<Iterable<Score>> getAllScores () {
        return ResponseEntity.ok(scoreService.getAllScores());
    }

    @GetMapping
    public ResponseEntity<Score> getAllScoreById (@RequestParam UUID id) {
        return ResponseEntity.ok(scoreService.getScoreById(id));
    }

    @GetMapping("/all/user")
    public ResponseEntity<Iterable<Score>> getAllScoresByUser (@RequestParam String username) {
        return ResponseEntity.ok(scoreService.getAllScoresByUser(username));
    }


    @GetMapping("/all/quiz")
    public ResponseEntity<Iterable<Score>> getAllScoresByQuiz (@RequestParam UUID id) {
        return ResponseEntity.ok(scoreService.getAllScoresByQuiz(id));
    }

    @PostMapping
    @ApiResponses(value = {@ApiResponse(code = 400, message = "Bad request", response = BadRequestException.class),})
    public ResponseEntity<Score> addScore(@RequestBody @Valid Score score) {
        return ResponseEntity.ok(scoreService.addScore(score));
    }

    @DeleteMapping
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad request", response = BadRequestException.class),
            @ApiResponse(code = 501, message = "Internal server error", response = InternalErrorException.class),})
    public ResponseEntity<JSONObject> deleteScore(@RequestParam UUID id) {
        return new ResponseEntity<>(scoreService.deleteScore(id), HttpStatus.OK);
    }
}
