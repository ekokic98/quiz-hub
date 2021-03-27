package com.quizhub.quiz.services;

import com.quizhub.quiz.exceptions.BadRequestException;
import com.quizhub.quiz.model.Answer;
import com.quizhub.quiz.repositories.AnswerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AnswerService {

    private final AnswerRepository answerRepository;

    public AnswerService(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    public Answer getAnswer(UUID id) {
        return answerRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Wrong answer id"));
    }

    public List<Answer> getAnswersByQuestionId(UUID id) {
        return answerRepository.findAllByQuestionId(id);
    }

    public Answer add(Answer answer) {
        return answerRepository.save(answer);
    }
}
