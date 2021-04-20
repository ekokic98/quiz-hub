package com.quizhub.property.services;

import com.quizhub.property.dto.Person;
import com.quizhub.property.dto.Quiz;
import com.quizhub.property.event.EventRequest;
import com.quizhub.property.exceptions.BadRequestException;
import com.quizhub.property.exceptions.ConflictException;
import com.quizhub.property.exceptions.InternalErrorException;
import com.quizhub.property.model.Rating;
import com.quizhub.property.repositories.RatingRepository;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.UUID;

import static com.quizhub.property.services.PropertyService.registerEvent;

@Service
public class RatingService {
    private final RatingRepository ratingRepository;
    private final RestTemplate restTemplate;

    public RatingService(RatingRepository RatingRepository, RestTemplate restTemplate) {
        this.ratingRepository = RatingRepository;
        this.restTemplate = restTemplate;
    }

    public Iterable<Rating> getAllRatings() {
        registerEvent(EventRequest.actionType.GET, "/api/property-ms/ratings/all", "200");
        return ratingRepository.findAll();
    }

    public Iterable<Rating> getAllRatingsByUser(String username) {
        Person person;
        try {
            person = restTemplate.getForObject("http://person-service/api/person-ms/persons/username?username=" + username, Person.class);
        } catch (Exception e) {
            throw new BadRequestException("Quiz or person does not exist");
        }
        return ratingRepository.getRatingByPerson(person.getId()).orElseThrow(() -> new BadRequestException("Person with username " + username + " does not exist"));
    }

    public Iterable<Rating> getAllRatingsByQuiz(UUID id) {
        return ratingRepository.getRatingByQuiz(id).orElseThrow(() -> new BadRequestException("Quiz with id " + id + " does not exist"));
    }

    public Rating getRatingById(UUID id) {
        return ratingRepository.findById(id).orElseThrow(() -> new BadRequestException("Rating with id " + id + " does not exist"));
    }

    public Rating addRating(Rating rating) {
        Quiz quiz;
        Person person;
        if (rating.getPerson() == null || rating.getQuiz() == null)
            throw new BadRequestException("Quiz or person cannot be null");
        try {
            person = restTemplate.getForObject("http://person-service/api/person-ms/persons?id=" + rating.getPerson(), Person.class);
            quiz = restTemplate.getForObject("http://quiz-service/api/quiz-ms/quizzes?id=" + rating.getQuiz(), Quiz.class);
            if (quiz.getId() == null || person.getId() == null)
                throw new BadRequestException("Quiz or person cannot be null");
        } catch (Exception e) {
            throw new BadRequestException("Quiz or person does not exist");
        }
        if (ratingRepository.existsByQuizAndPerson(rating.getQuiz(), rating.getPerson()))
            throw new ConflictException("Rating already exists");
        return ratingRepository.save(rating);
    }


    public Rating updateRating(Rating rating) {
        if (rating.getId() == null)
            throw new BadRequestException("Id cannot be null");
        Rating existingRating = ratingRepository.findById(rating.getId())
                .orElseThrow(() -> new BadRequestException("Rating ID is either incorrect or rating does not exist"));
        existingRating.setRate(rating.getRate());
        return ratingRepository.save(existingRating);
    }

    @Transactional
    public JSONObject deleteRating(UUID id) {
        if (!ratingRepository.existsById(id)) throw new BadRequestException("Rating with id " + id + " does not exist");
        ratingRepository.deleteById(id);
        if (ratingRepository.existsById(id))
            throw new InternalErrorException("Rating was not deleted (database issue)");
        return new JSONObject(new HashMap<String, String>() {{
            put("message", "Rating with id " + id.toString() + " has been successfully deleted");
        }});
    }


}
