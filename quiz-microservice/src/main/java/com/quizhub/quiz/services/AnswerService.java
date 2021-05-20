package com.quizhub.quiz.services;

import com.quizhub.quiz.event.EventRequest;
import com.quizhub.quiz.exceptions.BadRequestException;
import com.quizhub.quiz.model.Answer;
import com.quizhub.quiz.model.Question;
import com.quizhub.quiz.repositories.AnswerRepository;
import com.quizhub.quiz.repositories.QuestionRepository;
import com.quizhub.quiz.response.QA_Response;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.quizhub.quiz.services.QuizService.registerEvent;

@Service
public class AnswerService {

    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;

    public AnswerService(AnswerRepository answerRepository, QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
    }


    public Answer getAnswer(UUID id) {
        Optional<Answer> optionalAnswer = answerRepository.findById(id);
        if (optionalAnswer.isPresent()) {
            registerEvent(EventRequest.actionType.GET, "/api/answers", "200");
            return optionalAnswer.get();
        } else {
            registerEvent(EventRequest.actionType.GET, "/api/answers", "400");
            throw new BadRequestException("Wrong answer id");
        }
    }

    public List<Answer> getAnswersByQuestionId(UUID id) {
        registerEvent(EventRequest.actionType.GET, "/api/answers/question", "200");
        return answerRepository.findAllByQuestionId(id);
    }

    public Answer add(Answer answer) {
        registerEvent(EventRequest.actionType.CREATE, "/api/answers", "200");
        return answerRepository.save(answer);
    }

    public List<QA_Response> getQuestionsAndAnswersByQuizId (UUID id) {
        List<QA_Response> qa_response = new ArrayList<>();
        List<Question> questionList = questionRepository.findAllByQuizId(id);
        String correctAnswer = "";

        for (Question q: questionList) {
            List<Answer> answerList = answerRepository.findAllByQuestionId(q.getId());
            ArrayList<String> allAnswers = new ArrayList<>();
            for (Answer a: answerList) {
                if (a.getCorrect()) correctAnswer = a.getName();
                allAnswers.add(a.getName());
            }
            qa_response.add(new QA_Response(q.getId().toString(), q.getName(), q.getType().name(), allAnswers.indexOf(correctAnswer), allAnswers));
        }
        return qa_response;
    }
}
