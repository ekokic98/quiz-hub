package com.quizhub.property.services;

import com.quizhub.property.exceptions.BadRequestException;
import com.quizhub.property.exceptions.InternalErrorException;
import com.quizhub.property.model.Comment;
import com.quizhub.property.model.Favorite;
import com.quizhub.property.model.Person;
import com.quizhub.property.model.Quiz;
import com.quizhub.property.repositories.PersonRepository;
import com.quizhub.property.repositories.QuizRepository;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.UUID;

@Service
public class QuizService {
   private final QuizRepository quizRepository;
   private final PersonRepository personRepository;

   public QuizService (QuizRepository quizRepository, PersonRepository personRepository) {
       this.quizRepository = quizRepository;
       this.personRepository = personRepository;
   }

    public Iterable<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }

    public Iterable<Quiz> getAllQuizzesByUser (String username) {
        return quizRepository.getQuizByPerson(personRepository.findByUsername(username).orElseThrow(() -> new BadRequestException("Person with username " +
                username + " does not exist")));
    }

    public Quiz getQuizById (UUID id) {
        return quizRepository.findById(id).orElseThrow(() -> new BadRequestException("Favorite with id " + id.toString() + " does not exist"));
    }

    public Quiz addQuiz (Quiz quiz) {
        if (!(personRepository.existsById(quiz.getPerson().getId())))
            throw new BadRequestException("Person does not exist, check provided ID");
        return quizRepository.save(quiz);
    }

    public Quiz updateQuiz(Quiz quiz) {
        if (quiz.getId() == null)
            throw new BadRequestException("Id cannot be null");
        Quiz existingQuiz = quizRepository.findById(quiz.getId())
                .orElseThrow(() -> new BadRequestException("Quiz ID is either incorrect or comment does not exist"));
        existingQuiz.setName(quiz.getName());
        existingQuiz.setTotalQuestions(quiz.getTotalQuestions());
        return quizRepository.save(existingQuiz);
    }

    @Transactional
    public JSONObject deleteQuizById (UUID id) {
        if (!quizRepository.existsById(id)) throw new BadRequestException("Quiz with id " + id.toString() + " does not exist");
        quizRepository.deleteById(id);
        if (quizRepository.existsById(id)) throw new InternalErrorException("Quiz was not deleted (database issue)");
        JSONObject js = new JSONObject(new HashMap<String, String>() {{ put("message", "Quiz with id " + id.toString() + " has been successfully deleted");}});
        return js;
    }

}

