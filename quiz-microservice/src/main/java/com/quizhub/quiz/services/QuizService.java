package com.quizhub.quiz.services;

import com.quizhub.property.exceptions.InternalErrorException;
import com.quizhub.quiz.controllers.QuizController;
import com.quizhub.quiz.exceptions.BadRequestException;
import com.quizhub.quiz.exceptions.ConflictException;
import com.quizhub.quiz.model.*;
import com.quizhub.quiz.model.enums.QuestionType;
import com.quizhub.quiz.repositories.*;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Service
public class QuizService {

    private final QuizRepository quizRepository;
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final PersonRepository personRepository;
    private final CategoryRepository categoryRepository;
    private final RestTemplate restTemplate;

    public QuizService(QuizRepository quizRepository, QuestionRepository questionRepository, AnswerRepository answerRepository, PersonRepository personRepository, CategoryRepository categoryRepository, RestTemplate restTemplate) {
        this.quizRepository = quizRepository;
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
        this.personRepository = personRepository;
        this.categoryRepository = categoryRepository;
        this.restTemplate = restTemplate;
    }

    public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }

    public Quiz add(Quiz quiz) {
        Person savedPerson = restTemplate.getForObject("http//person-service/persons", Person.class);
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
}
