package com.quizhub.quiz.services;

import com.quizhub.quiz.event.EventRequest;
import com.quizhub.quiz.exceptions.BadRequestException;
import com.quizhub.quiz.model.Question;
import com.quizhub.quiz.repositories.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.quizhub.quiz.services.QuizService.registerEvent;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public Question getQuestion(UUID id) {
        Optional<Question> optionalQuestion = questionRepository.findById(id);
        if (optionalQuestion.isPresent()) {
            registerEvent(EventRequest.actionType.GET, "/api/questions/quiz", "200");
            return optionalQuestion.get();
        } else {
            registerEvent(EventRequest.actionType.GET, "/api/questions/quiz", "400");
            throw new BadRequestException("Wrong question id");
        }
    }

    public Question add(Question question) {
        registerEvent(EventRequest.actionType.CREATE, "/api/questions", "200");
        return questionRepository.save(question);
    }

    public List<Question> getQuestionsByQuizId(UUID id) {
        registerEvent(EventRequest.actionType.GET, "/api/questions/quiz", "200");
        return questionRepository.findAllByQuizId(id);
    }
}
