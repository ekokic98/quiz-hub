package com.quizhub.property.controllers;

import com.quizhub.property.exceptions.BadRequestException;
import com.quizhub.property.exceptions.InternalErrorException;
import com.quizhub.property.model.Rating;
import com.quizhub.property.services.RatingService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/ratings")
public class RatingController {
    private final RatingService ratingService;

    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @GetMapping("/all")
    public ResponseEntity<Iterable<Rating>> getAllRating() {
        return ResponseEntity.ok(ratingService.getAllRatings());
    }

    @GetMapping
    public ResponseEntity<Rating> getAllRatingsById(@RequestParam UUID id) {
        return ResponseEntity.ok(ratingService.getRatingById(id));
    }

    @GetMapping("/all/user")
    public ResponseEntity<Iterable<Rating>> getAllRatingsByUser(@RequestParam String username) {
        return ResponseEntity.ok(ratingService.getAllRatingsByUser(username));
    }

    @GetMapping("/all/quiz")
    public ResponseEntity<Iterable<Rating>> getAllRatingsByQuiz(@RequestParam UUID id) {
        return ResponseEntity.ok(ratingService.getAllRatingsByQuiz(id));
    }

    @GetMapping("/quiz")
    public ResponseEntity<Optional<Rating>> getRatingByUserAndQuiz(@RequestParam UUID userId, @RequestParam UUID quizId) {
        return ResponseEntity.ok(ratingService.getRatingByUserAndQuiz(userId, quizId));
    }

    @PostMapping
    @ApiResponses(value = {@ApiResponse(code = 400, message = "Bad request", response = BadRequestException.class),})
    public ResponseEntity<Rating> addScore(@RequestBody @Valid Rating Rating) {
        return ResponseEntity.ok(ratingService.addRating(Rating));
    }

    @PutMapping
    @ApiResponses(value = {@ApiResponse(code = 400, message = "Bad request", response = BadRequestException.class),})
    public ResponseEntity<Rating> updateComment(@RequestBody @Valid Rating rating) {
        return ResponseEntity.ok(ratingService.updateRating(rating));
    }

    @DeleteMapping
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad request", response = BadRequestException.class),
            @ApiResponse(code = 501, message = "Internal server error", response = InternalErrorException.class),})
    public ResponseEntity<JSONObject> deleteScore(@RequestParam UUID id) {
        return new ResponseEntity<>(ratingService.deleteRating(id), HttpStatus.OK);
    }
}
