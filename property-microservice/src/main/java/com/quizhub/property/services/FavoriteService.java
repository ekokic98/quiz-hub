package com.quizhub.property.services;

import com.quizhub.property.dto.Person;
import com.quizhub.property.dto.Quiz;
import com.quizhub.property.event.EventRequest;
import com.quizhub.property.exceptions.BadRequestException;
import com.quizhub.property.exceptions.ConflictException;
import com.quizhub.property.exceptions.InternalErrorException;
import com.quizhub.property.model.Favorite;
import com.quizhub.property.repositories.FavoriteRepository;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.UUID;

import static com.quizhub.property.services.PropertyService.registerEvent;

@Service
public class FavoriteService {
    private final FavoriteRepository favoriteRepository;
    private final RestTemplate restTemplate;

    public FavoriteService(FavoriteRepository favoriteRepository, RestTemplate restTemplate) {
        this.favoriteRepository = favoriteRepository;
        this.restTemplate = restTemplate;
    }

    public Iterable<Favorite> getAllFavorites() {
        registerEvent(EventRequest.actionType.GET, "/api/property-ms/favorites/all", "200");
        return favoriteRepository.findAll();
    }

    public Iterable<Favorite> getAllFavoritesByUser(String username) {
        Person person;
        try {
            person = restTemplate.getForObject("http://person-service/api/person-ms/persons/username?username=" + username, Person.class);
        } catch (Exception e) {
            throw new BadRequestException("Quiz or person does not exist");
        }
        return favoriteRepository.getFavoriteByPerson(person.getId()).orElseThrow(() -> new BadRequestException("Person with username " +
                username + " does not exist"));
    }

    public Iterable<Favorite> getAllFavoritesByQuiz(UUID id) {
        return favoriteRepository.getFavoriteByQuiz(id).orElseThrow(() -> new BadRequestException("Quiz with id " +
                id.toString() + " does not exist"));
    }

    public Favorite getFavoriteById(UUID id) {
        return favoriteRepository.findById(id).orElseThrow(() -> new BadRequestException("Favorite with id " + id + " does not exist"));
    }

    public Favorite addFavorite(Favorite favorite) {
        Quiz quiz;
        Person person;
        if (favorite.getPerson() == null || favorite.getQuiz() == null)
            throw new BadRequestException("Quiz or person cannot be null");
        try {
            person = restTemplate.getForObject("http://person-service/api/person-ms/persons?id=" + favorite.getPerson(), Person.class);
            quiz = restTemplate.getForObject("http://quiz-service/api/quiz-ms/quizzes?id=" + favorite.getQuiz(), Quiz.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BadRequestException("Quiz or person does not exist");
        }
        if (favoriteRepository.existsByQuizAndPerson(favorite.getQuiz(), favorite.getPerson()))
            throw new ConflictException("Favorite already exists");

        return favoriteRepository.save(favorite);
    }

    @Transactional
    public JSONObject deleteFavorite(UUID id) {
        if (!favoriteRepository.existsById(id))
            throw new BadRequestException("Favorite with id " + id + " does not exist");
        favoriteRepository.deleteById(id);
        if (favoriteRepository.existsById(id))
            throw new InternalErrorException("Favorite was not deleted (database issue)");
        return new JSONObject(new HashMap<String, String>() {{
            put("message", "Favorite with id " + id.toString() + " has been successfully deleted");
        }});
    }
}

