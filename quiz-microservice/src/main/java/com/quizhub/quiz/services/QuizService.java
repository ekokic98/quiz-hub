package com.quizhub.quiz.services;

import com.quizhub.quiz.exceptions.BadRequestException;
import com.quizhub.quiz.exceptions.ConflictException;
import com.quizhub.quiz.model.Quiz;
import com.quizhub.quiz.repositories.QuizRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class QuizService {

    private final QuizRepository quizRepository;

    public QuizService(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }

    public Quiz add(Quiz quiz) {
        if (quizRepository.existsByName(quiz.getName()))
            throw new ConflictException("Name already in use");
        return quizRepository.save(quiz);
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
}
