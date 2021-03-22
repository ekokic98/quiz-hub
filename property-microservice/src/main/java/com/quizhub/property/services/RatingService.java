package com.quizhub.property.services;

import com.quizhub.property.exceptions.BadRequestException;
import com.quizhub.property.exceptions.ConflictException;
import com.quizhub.property.exceptions.InternalErrorException;
import com.quizhub.property.model.Rating;
import com.quizhub.property.repositories.PersonRepository;
import com.quizhub.property.repositories.QuizRepository;
import com.quizhub.property.repositories.RatingRepository;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.UUID;

@Service
public class RatingService {
    private final RatingRepository ratingRepository;
    private final PersonRepository personRepository;
    private final QuizRepository quizRepository;

    public RatingService(RatingRepository RatingRepository, PersonRepository personRepository, QuizRepository quizRepository) {
        this.ratingRepository = RatingRepository;
        this.personRepository = personRepository;
        this.quizRepository = quizRepository;
    }

    public Iterable<Rating> getAllRatings() {
        return ratingRepository.findAll();
    }

    public Iterable<Rating> getAllRatingsByUser(String username) {
        return ratingRepository.getRatingByPerson(personRepository.findByUsername(username).orElseThrow(() -> new BadRequestException("Person with username " +
                username + " does not exist")));
    }

    public Iterable<Rating> getAllRatingsByQuiz(UUID id) {
        return ratingRepository.getRatingByQuiz(quizRepository.findById(id).orElseThrow(() -> new BadRequestException("Quiz with id " +
                id.toString() + " does not exist")));
    }

    public Rating getRatingById(UUID id) {
        return ratingRepository.findById(id).orElseThrow(() -> new BadRequestException("Rating with id " + id.toString() + " does not exist"));
    }

    public Rating addRating (Rating rating) {
        if (!(personRepository.existsById(rating.getPerson().getId()) && quizRepository.existsById(rating.getQuiz().getId())))
            throw new BadRequestException("Quiz or person does not exist, check provided IDs");
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
        if (!ratingRepository.existsById(id)) throw new BadRequestException("Rating with id " + id.toString() + " does not exist");
        ratingRepository.deleteById(id);
        if (ratingRepository.existsById(id)) throw new InternalErrorException("Rating was not deleted (database issue)");
        JSONObject js = new JSONObject(new HashMap<String, String>() {{ put("message", "Rating with id " + id.toString() + " has been successfully deleted");}});
        return js;
    }


}
