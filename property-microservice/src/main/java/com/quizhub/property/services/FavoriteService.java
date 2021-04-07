package com.quizhub.property.services;

import com.quizhub.property.exceptions.BadRequestException;
import com.quizhub.property.exceptions.ConflictException;
import com.quizhub.property.exceptions.InternalErrorException;
import com.quizhub.property.model.Favorite;
import com.quizhub.property.model.Person;
import com.quizhub.property.model.Quiz;
import com.quizhub.property.repositories.FavoriteRepository;
import com.quizhub.property.repositories.PersonRepository;
import com.quizhub.property.repositories.QuizRepository;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.UUID;

@Service
public class FavoriteService {
    private final FavoriteRepository favoriteRepository;
    private final PersonRepository personRepository;
    private final QuizRepository quizRepository;
    private final RestTemplate restTemplate;

    public FavoriteService(FavoriteRepository favoriteRepository, PersonRepository personRepository, QuizRepository quizRepository, RestTemplate restTemplate) {
        this.favoriteRepository = favoriteRepository;
        this.personRepository = personRepository;
        this.quizRepository = quizRepository;
        this.restTemplate = restTemplate;
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
        Quiz quiz = null;
        Person person = null;
        if (favorite.getPerson()==null || favorite.getQuiz()==null) throw new BadRequestException("Quiz or person cannot be null");
        try {
            //fetch quiz
            quiz = restTemplate.getForObject("http://quiz-service/api/quiz-ms/quizzes?id=" + favorite.getQuiz().getId(), Quiz.class);
            //fetch person - tvoj dio kerime
            // person = restTemplate.getForObject("http://person-service/api/quiz-ms/person?id=" + favorite.getPerson().getId(), Person.class);
            //mora postojati u lokalnoj bazi da bi uopce mogli dodati
            if (!(personRepository.existsById(favorite.getPerson().getId()))) {
                // personRepository.save(person);
            }
            if (!(quizRepository.existsById(favorite.getQuiz().getId())))  {
                quizRepository.save(quiz);
            }
            System.out.println(quiz.toString());
        }
        catch (Exception e) {
            throw new BadRequestException("Quiz or person does not exist");
        }
        // obavezno postaviti osobu i kviz koji se povuku iz drugog servisa
       // favorite.setPerson(person);
        favorite.setQuiz(quiz);
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

