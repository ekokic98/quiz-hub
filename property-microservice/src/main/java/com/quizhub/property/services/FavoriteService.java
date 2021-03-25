package com.quizhub.property.services;

import com.quizhub.property.exceptions.BadRequestException;
import com.quizhub.property.exceptions.ConflictException;
import com.quizhub.property.exceptions.InternalErrorException;
import com.quizhub.property.model.Favorite;
import com.quizhub.property.repositories.FavoriteRepository;
import com.quizhub.property.repositories.PersonRepository;
import com.quizhub.property.repositories.QuizRepository;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.UUID;

@Service
public class FavoriteService {
    private final FavoriteRepository favoriteRepository;
    private final PersonRepository personRepository;
    private final QuizRepository quizRepository;

    public FavoriteService(FavoriteRepository favoriteRepository, PersonRepository personRepository, QuizRepository quizRepository) {
        this.favoriteRepository = favoriteRepository;
        this.personRepository = personRepository;
        this.quizRepository = quizRepository;
    }

    public Iterable<Favorite> getAllFavorites () {
        return favoriteRepository.findAll();
    }

    public Iterable<Favorite> getAllFavoritesByUser (String username) {
        return favoriteRepository.getFavoriteByPerson(personRepository.findByUsername(username).orElseThrow(() -> new BadRequestException("Person with username " +
            username + " does not exist")));
    }

    public Iterable<Favorite> getAllFavoritesByQuiz (UUID id) {
        return favoriteRepository.getFavoriteByQuiz(quizRepository.findById(id).orElseThrow(() -> new BadRequestException("Quiz with id " +
                id.toString() + " does not exist")));
    }

    public Favorite getFavoriteById (UUID id) {
        return favoriteRepository.findById(id).orElseThrow(() -> new BadRequestException("Favorite with id " + id.toString() + " does not exist"));
    }

    public Favorite addFavorite (Favorite favorite) {
        if (favorite.getPerson()==null || favorite.getQuiz()==null || !(personRepository.existsById(favorite.getPerson().getId()) && quizRepository.existsById(favorite.getQuiz().getId())))
            throw new BadRequestException("Quiz or person does not exist, check provided IDs");
        if (favoriteRepository.existsByQuizAndPerson(favorite.getQuiz(), favorite.getPerson()))
            throw new ConflictException("Favorite already exists");
        return favoriteRepository.save(favorite);
    }

    @Transactional
    public JSONObject deleteFavorite(UUID id) {
        if (!favoriteRepository.existsById(id)) throw new BadRequestException("Favorite with id " + id.toString() + " does not exist");
        favoriteRepository.deleteById(id);
        if (favoriteRepository.existsById(id)) throw new InternalErrorException("Favorite was not deleted (database issue)");
        JSONObject js = new JSONObject(new HashMap<String, String>() {{ put("message", "Favorite with id " + id.toString() + " has been successfully deleted");}});
        return js;
    }
}

