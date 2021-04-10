package com.quizhub.tournament.controllers;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.quizhub.tournament.dto.Quiz;
import com.quizhub.tournament.exceptions.BadRequestException;
import com.quizhub.tournament.exceptions.ConflictException;
import com.quizhub.tournament.model.Person;
import com.quizhub.tournament.model.Tournament;
import com.quizhub.tournament.services.TournamentService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/tournament-ms/tournaments")
public class TournamentController {

    private final TournamentService tournamentService;

    public TournamentController(TournamentService tournamentService) {
        this.tournamentService = tournamentService;
    }

    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad request", response = BadRequestException.class),
            @ApiResponse(code = 409, message = "Conflict", response = ConflictException.class),
    })
    public ResponseEntity<Tournament> add(@RequestBody @Valid Tournament tournament) {
        return ResponseEntity.ok(tournamentService.add(tournament));
    }

    @PostMapping("/quiz")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad request", response = BadRequestException.class),
    })
    public ResponseEntity<Quiz> addGeneratedQuizToTournament(@RequestBody @Valid QuizParams quizParams) {
        return ResponseEntity.ok(tournamentService.addGeneratedQuizToTournament(quizParams));
    }

    @PutMapping
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad request", response = BadRequestException.class),
            @ApiResponse(code = 409, message = "Conflict", response = ConflictException.class),
    })
    public ResponseEntity<Tournament> update(@RequestBody @Valid Tournament tournament) {
        return ResponseEntity.ok(tournamentService.update(tournament));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Tournament>> getAllTournaments() {
        return ResponseEntity.ok(tournamentService.getAllTournaments());
    }

    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad request", response = BadRequestException.class),
    })
    public ResponseEntity<Tournament> getTournament(@RequestParam UUID id) {
        return ResponseEntity.ok(tournamentService.getTournament(id));
    }

    @GetMapping("/leaderboard")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad request", response = BadRequestException.class),
    })
    public ResponseEntity<List<Person>> getLeaderboardForTournament(@RequestParam UUID id) {
        return ResponseEntity.ok(tournamentService.getLeaderboardForTournament(id));
    }

    public static class QuizParams {
        private final UUID tournamentId;
        @Min(value = 0, message = "Amount of questions must be specified")
        private final Integer amount;
        private final String difficulty;
        private final Integer category;
        private final String type;

        public QuizParams(@JsonProperty("tournamentId") UUID tournamentId,
                          @JsonProperty("amount") Integer amount,
                          @JsonProperty("difficulty") String difficulty,
                          @JsonProperty("category") Integer category,
                          @JsonProperty("type") String type) {
            this.tournamentId = tournamentId;
            this.amount = amount;
            this.difficulty = difficulty;
            this.category = category;
            this.type = type;
        }

        public UUID getTournamentId() {
            return tournamentId;
        }

        public Integer getAmount() {
            return amount;
        }

        public String getDifficulty() {
            return difficulty;
        }

        public Integer getCategory() {
            return category;
        }

        public String getType() {
            return type;
        }
    }
}
