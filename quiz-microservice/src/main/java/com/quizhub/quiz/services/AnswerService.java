package com.quizhub.quiz.services;

import com.quizhub.quiz.event.EventRequest;
import com.quizhub.quiz.exceptions.BadRequestException;
import com.quizhub.quiz.model.Answer;
import com.quizhub.quiz.repositories.AnswerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.quizhub.quiz.services.QuizService.registerEvent;

@Service
public class AnswerService {

    private final AnswerRepository answerRepository;

    public AnswerService(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    public Answer getAnswer(UUID id) {
        Optional<Answer> optionalAnswer = answerRepository.findById(id);
        if (optionalAnswer.isPresent()) {
            registerEvent(EventRequest.actionType.GET, "/api/quiz-ms/answers", "200");
            return optionalAnswer.get();
        } else {
            registerEvent(EventRequest.actionType.GET, "/api/quiz-ms/answers", "400");
            throw new BadRequestException("Wrong answer id");
        }
    }

    public List<Answer> getAnswersByQuestionId(UUID id) {
        registerEvent(EventRequest.actionType.GET, "/api/quiz-ms/answers/question", "200");
        return answerRepository.findAllByQuestionId(id);
    }

    public Answer add(Answer answer) {
        registerEvent(EventRequest.actionType.CREATE, "/api/quiz-ms/answers", "200");
        return answerRepository.save(answer);
    }
}
