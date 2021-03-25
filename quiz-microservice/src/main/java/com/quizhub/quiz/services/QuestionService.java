package com.quizhub.quiz.services;

import com.quizhub.quiz.exceptions.BadRequestException;
import com.quizhub.quiz.model.Question;
import com.quizhub.quiz.repositories.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public Question getQuestion(UUID id) {
        return questionRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Wrong question id"));
    }

    public Question add(Question question) {
        return questionRepository.save(question);
    }

    public List<Question> getQuestionsByQuizId(UUID id) {
        return questionRepository.findAllByQuizId(id);
    }
}
