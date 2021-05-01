package com.quizhub.property.controllers;

import com.quizhub.property.exceptions.BadRequestException;
import com.quizhub.property.exceptions.InternalErrorException;
import com.quizhub.property.model.Favorite;
import com.quizhub.property.services.FavoriteService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping(path="/api/favorites")
public class FavoriteController {

    private FavoriteService favoriteService;

    public FavoriteController(FavoriteService favoriteService) {
        this.favoriteService = favoriteService;
    }

    @GetMapping("/all")
    public ResponseEntity<Iterable<Favorite>> getAllFavorites() {
        return ResponseEntity.ok(favoriteService.getAllFavorites());
    }

    @GetMapping
    public ResponseEntity<Favorite> getAllFavoritesById(@RequestParam UUID id) {
        return ResponseEntity.ok(favoriteService.getFavoriteById(id));
    }

    @GetMapping("/all/user")
    public ResponseEntity<Iterable<Favorite>> getAllFavoritesByUser(@RequestParam String username) {
        return ResponseEntity.ok(favoriteService.getAllFavoritesByUser(username));
    }

    @GetMapping("/all/quiz")
    public ResponseEntity<Iterable<Favorite>> getAllFavoritesByQuiz(@RequestParam UUID id) {
        return ResponseEntity.ok(favoriteService.getAllFavoritesByQuiz(id));
    }

    @PostMapping
    @ApiResponses(value = {@ApiResponse(code = 400, message = "Bad request", response = BadRequestException.class),})
    public ResponseEntity<Favorite> addFavorite(@RequestBody @Valid Favorite favorite) {
        return ResponseEntity.ok(favoriteService.addFavorite(favorite));
    }

    @DeleteMapping
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad request", response = BadRequestException.class),
            @ApiResponse(code = 501, message = "Internal server error", response = InternalErrorException.class),})
    public ResponseEntity<JSONObject> deleteFavorite(@RequestParam UUID id) {
        return new ResponseEntity<>(favoriteService.deleteFavorite(id), HttpStatus.OK);
    }

}
