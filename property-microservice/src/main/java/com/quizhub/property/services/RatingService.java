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
import java.util.Optional;
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
        registerEvent(EventRequest.actionType.GET, "/api/ratings/all", "200");
        return ratingRepository.findAll();
    }

    public Rating getRatingById(UUID id) {
        registerEvent(EventRequest.actionType.GET, "/api/ratings", "200");
        return ratingRepository.findById(id)
                .orElseThrow(() -> {
                    registerEvent(EventRequest.actionType.GET, "/api/ratings", "400");
                    return new BadRequestException("Rating with id " + id + " does not exist");
                });
    }

    public Iterable<Rating> getAllRatingsByUser(String username) {
        Person person;
        try {
            person = restTemplate.getForObject("http://person-service/api/persons/username?username=" + username, Person.class);
        } catch (Exception e) {
            throw new BadRequestException("Quiz or person does not exist");
        }
        registerEvent(EventRequest.actionType.GET, "/api/ratings/all/user", "200");
        return ratingRepository.getRatingByPerson(person.getId())
                .orElseThrow(() -> {
                    registerEvent(EventRequest.actionType.GET, "/api/ratings/all/user", "400");
                    return new BadRequestException("Person with username " + username + " does not exist");
                });
    }

    public Iterable<Rating> getAllRatingsByQuiz(UUID id) {
        registerEvent(EventRequest.actionType.GET, "/api/ratings/all/quiz", "200");
        return ratingRepository.getRatingByQuiz(id)
                .orElseThrow(() -> {
                    registerEvent(EventRequest.actionType.GET, "/api/ratings/all/quiz", "400");
                    return new BadRequestException("Quiz with id " + id + " does not exist");
                });
    }

    public Optional<Rating> getRatingByUserAndQuiz(UUID userId, UUID quizId) {
        return ratingRepository.findByQuizAndPerson(quizId, userId);
    }

    public Rating addRating(Rating rating) {
        try {
            Quiz quiz;
            Person person;
            if (rating.getPerson() == null || rating.getQuiz() == null) {
                throw new BadRequestException("Quiz or person cannot be null");
            }
            try {
                person = restTemplate.getForObject("http://person-service/api/persons?id=" + rating.getPerson(), Person.class);
                quiz = restTemplate.getForObject("http://quiz-service/api/quizzes?id=" + rating.getQuiz(), Quiz.class);
                if (quiz.getId() == null || person.getId() == null) {
                    throw new BadRequestException("Quiz or person cannot be null");
                }
            } catch (Exception e) {
                throw new BadRequestException("Quiz or person does not exist");
            }
            Rating savedRating = ratingRepository.findByQuizAndPerson(rating.getQuiz(), rating.getPerson())
                    .orElse(rating);
            savedRating.setRate(rating.getRate());
            registerEvent(EventRequest.actionType.CREATE, "/api/ratings", "200");
            return ratingRepository.save(savedRating);
        } catch (ConflictException exception) {
            registerEvent(EventRequest.actionType.CREATE, "/api/ratings", "409");
            throw exception;
        } catch (BadRequestException exception) {
            registerEvent(EventRequest.actionType.CREATE, "/api/ratings", "400");
            throw exception;
        }
    }

    public Rating updateRating(Rating rating) {
        try {
            if (rating.getId() == null) {
                throw new BadRequestException("Id cannot be null");
            }
            Rating existingRating = ratingRepository.findById(rating.getId())
                    .orElseThrow(() -> new BadRequestException("Rating ID is either incorrect or rating does not exist"));
            existingRating.setRate(rating.getRate());
            registerEvent(EventRequest.actionType.UPDATE, "/api/ratings", "200");
            return ratingRepository.save(existingRating);
        } catch (BadRequestException exception) {
            registerEvent(EventRequest.actionType.UPDATE, "/api/ratings", "400");
            throw exception;
        }
    }

    @Transactional
    public JSONObject deleteRating(UUID id) {
        try {
            if (!ratingRepository.existsById(id)) {
                throw new BadRequestException("Rating with id " + id + " does not exist");
            }
            ratingRepository.deleteById(id);
            if (ratingRepository.existsById(id)) {
                throw new InternalErrorException("Rating was not deleted (database issue)");
            }
            registerEvent(EventRequest.actionType.DELETE, "/api/ratings", "200");
            return new JSONObject(new HashMap<String, String>() {{
                put("message", "Rating with id " + id.toString() + " has been successfully deleted");
            }});
        } catch (BadRequestException exception) {
            registerEvent(EventRequest.actionType.DELETE, "/api/ratings", "400");
            throw exception;
        }
    }
}
