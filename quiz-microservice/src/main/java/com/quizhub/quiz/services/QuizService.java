package com.quizhub.quiz.services;

import com.quizhub.property.exceptions.InternalErrorException;
import com.quizhub.quiz.exceptions.BadRequestException;
import com.quizhub.quiz.exceptions.ConflictException;
import com.quizhub.quiz.model.Category;
import com.quizhub.quiz.model.Person;
import com.quizhub.quiz.model.Quiz;
import com.quizhub.quiz.repositories.CategoryRepository;
import com.quizhub.quiz.repositories.PersonRepository;
import com.quizhub.quiz.repositories.QuizRepository;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Service
public class QuizService {

    private final QuizRepository quizRepository;
    private final PersonRepository personRepository;
    private final CategoryRepository categoryRepository;

    public QuizService(QuizRepository quizRepository, PersonRepository personRepository, CategoryRepository categoryRepository) {
        this.quizRepository = quizRepository;
        this.personRepository = personRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }

    public Quiz add(Quiz quiz) {
        Person savedPerson = personRepository.save(quiz.getPerson());
        Category savedCategory = categoryRepository.save(quiz.getCategory());
        if (quizRepository.existsByName(quiz.getName()))
            throw new ConflictException("Name already in use");
        return quizRepository.save(new Quiz(
                quiz.getId(),
                savedPerson,
                savedCategory,
                quiz.getName(),
                quiz.getDateCreated(),
                quiz.getTimeLimit(),
                quiz.getTotalQuestions()
        ));
    }

    public Quiz getQuiz(UUID id) {
        return quizRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Wrong quiz id"));
    }

    public List<Quiz> getQuizzesByCategory(UUID id) {
        return quizRepository.findAllByCategoryId(id);
    }

    public List<Quiz> getQuizzesByName(String name) {
        return quizRepository.getQuizzesByName(name);
    }

    public Quiz getRandomQuiz() {
        return quizRepository.getRandomQuiz()
                .orElseThrow(() -> new BadRequestException("No active quizzes in database"));
    }

    @Transactional
    public JSONObject deleteQuizById (UUID id) {
        if (!quizRepository.existsById(id)) throw new com.quizhub.property.exceptions.BadRequestException("Quiz with id " + id.toString() + " does not exist");
        quizRepository.deleteById(id);
        if (quizRepository.existsById(id)) throw new InternalErrorException("Quiz was not deleted (database issue)");
        JSONObject js = new JSONObject(new HashMap<String, String>() {{ put("message", "Quiz with id " + id.toString() + " has been successfully deleted");}});
        return js;
    }
}
