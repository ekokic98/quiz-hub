package com.quizhub.tournament.controllers;

import com.quizhub.tournament.exceptions.BadRequestException;
import com.quizhub.tournament.exceptions.ConflictException;
import com.quizhub.tournament.model.Person;
import com.quizhub.tournament.model.Quiz;
import com.quizhub.tournament.model.Tournament;
import com.quizhub.tournament.services.TournamentService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/tournament-service/tournaments")
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

    @GetMapping("/quizzes")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad request", response = BadRequestException.class),
    })
    public ResponseEntity<List<Quiz>> getQuizzesForTournament(@RequestParam UUID id) {
        return ResponseEntity.ok(tournamentService.getQuizzesForTournament(id));
    }

    @GetMapping("/leaderboard")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad request", response = BadRequestException.class),
    })
    public ResponseEntity<List<Person>> getLeaderboardForTournament(@RequestParam UUID id) {
        return ResponseEntity.ok(tournamentService.getLeaderboardForTournament(id));
    }
}
