package com.quizhub.quiz.services;

import com.quizhub.quiz.event.EventRequest;
import com.quizhub.quiz.exceptions.BadRequestException;
import com.quizhub.quiz.model.Answer;
import com.quizhub.quiz.model.Question;
import com.quizhub.quiz.model.Quiz;
import com.quizhub.quiz.repositories.AnswerRepository;
import com.quizhub.quiz.repositories.QuestionRepository;
import com.quizhub.quiz.repositories.QuizRepository;
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
    private final QuizRepository quizRepository;

    public AnswerService(AnswerRepository answerRepository, QuestionRepository questionRepository, QuizRepository quizRepository) {
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
        this.quizRepository = quizRepository;
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
        Optional<Quiz> pQuiz = quizRepository.getQuizById(id);
        Quiz quiz = new Quiz();
        if (pQuiz.isPresent()) {
            quiz = pQuiz.get();
        }
        else {
            throw new BadRequestException("Quiz does not exist!");
        }

        String correctAnswer = "";

        for (Question q: questionList) {
            List<Answer> answerList = answerRepository.findAllByQuestionId(q.getId());
            ArrayList<String> incorrectAnswers = new ArrayList<>();
            for (Answer a: answerList) {
                if (a.getCorrect()) correctAnswer = a.getName();
                else incorrectAnswers.add(a.getName());
            }
            ArrayList<String> options = (ArrayList<String>) incorrectAnswers.clone();
            options.add(correctAnswer);
            qa_response.add(new QA_Response(q.getId().toString(), quiz.getCategory().getName(), "multiple", q.getName(), correctAnswer, incorrectAnswers, options));
        }
        return qa_response;
    }
}
