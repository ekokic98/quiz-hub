package com.quizhub.quiz.services;

import com.quizhub.property.exceptions.InternalErrorException;
import com.quizhub.quiz.controllers.QuizController;
import com.quizhub.quiz.dto.Person;
import com.quizhub.quiz.exceptions.BadRequestException;
import com.quizhub.quiz.exceptions.ConflictException;
import com.quizhub.quiz.exceptions.ServiceUnavailableException;
import com.quizhub.quiz.model.Answer;
import com.quizhub.quiz.model.Category;
import com.quizhub.quiz.model.Question;
import com.quizhub.quiz.model.Quiz;
import com.quizhub.quiz.model.enums.QuestionType;
import com.quizhub.quiz.repositories.AnswerRepository;
import com.quizhub.quiz.repositories.CategoryRepository;
import com.quizhub.quiz.repositories.QuestionRepository;
import com.quizhub.quiz.repositories.QuizRepository;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class QuizService {

    private final QuizRepository quizRepository;
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final CategoryRepository categoryRepository;
    private final RestTemplate restTemplate;

    public QuizService(QuizRepository quizRepository, QuestionRepository questionRepository, AnswerRepository answerRepository, CategoryRepository categoryRepository, RestTemplate restTemplate) {
        this.quizRepository = quizRepository;
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
        this.categoryRepository = categoryRepository;
        this.restTemplate = restTemplate;
    }

    public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }

    public Quiz add(Quiz quiz) {
        Person person;

        try {
        person = restTemplate.getForObject(
                "http://person-service/api/person-ms/persons?id=" + quiz.getPersonId().toString(),
                Person.class
        );
        } catch (ResourceAccessException exception) {
            throw new ServiceUnavailableException("Error while communicating with another microservice.");
        }

        if (person == null) {
            throw new InternalErrorException("Error while communicating with person service.");
        }

        if (quizRepository.existsByName(quiz.getName()))
            throw new ConflictException("Name already in use");

        Optional<Category> savedCategory = categoryRepository.findById(quiz.getCategory().getId());

        Category quizCategory;

        if (savedCategory.isEmpty()) {
            if (categoryRepository.existsByName(quiz.getCategory().getName())) {
                throw new BadRequestException("Category name already exists.");
            }
            quizCategory = categoryRepository.save(quiz.getCategory());
        } else {
            quizCategory = savedCategory.get();
        }

        return quizRepository.save(new Quiz(
                quiz.getId(),
                person.getId(),
                quizCategory,
                quiz.getName(),
                null,
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
    public JSONObject deleteQuizById(UUID id) {
        if (!quizRepository.existsById(id))
            throw new BadRequestException("Quiz with id " + id.toString() + " does not exist");
        quizRepository.deleteById(id);
        if (quizRepository.existsById(id)) throw new InternalErrorException("Quiz was not deleted (database issue)");
        JSONObject js = new JSONObject(new HashMap<String, String>() {{
            put("message", "Quiz with id " + id.toString() + " has been successfully deleted");
        }});
        return js;
    }

    public Quiz addQuizFromTournament(QuizController.TournamentQuiz tournamentQuiz) {
        Quiz quiz = new Quiz();
        quiz.setName(LocalDateTime.now().toString());
        quiz.setTimeLimit(tournamentQuiz.getQuestionsLength() * 15);
        quiz.setTotalQuestions(tournamentQuiz.getQuestionsLength());
        Quiz savedQuiz = quizRepository.save(quiz);
        tournamentQuiz.getQuestions();
        for (var question : tournamentQuiz.getQuestions()) {
            Question savedQuestion = questionRepository.save(new Question(savedQuiz, question.getName(), QuestionType.SINGLE_CHOICE));
            answerRepository.save(new Answer(savedQuestion, question.getCorrectAnswer(), true));
            for (var answer : question.getIncorrectAnswers()) {
                answerRepository.save(new Answer(savedQuestion, answer, false));
            }
        }
        return savedQuiz;
    }

    public List<Quiz> getQuizzesForTournament(UUID id) {
        return quizRepository.findAllByTournamentId(id);
    }
}
