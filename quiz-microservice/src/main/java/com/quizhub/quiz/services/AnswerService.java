package com.quizhub.quiz.services;

import com.quizhub.quiz.dto.Person;
import com.quizhub.quiz.event.EventRequest;
import com.quizhub.quiz.exceptions.BadRequestException;
import com.quizhub.quiz.exceptions.ServiceUnavailableException;
import com.quizhub.quiz.model.Answer;
import com.quizhub.quiz.model.Question;
import com.quizhub.quiz.model.Quiz;
import com.quizhub.quiz.repositories.AnswerRepository;
import com.quizhub.quiz.repositories.QuestionRepository;
import com.quizhub.quiz.repositories.QuizRepository;
import com.quizhub.quiz.response.QA_Response;
import com.quizhub.quiz.response.QA_Response_Wrapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

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
    private final RestTemplate restTemplate;

    public AnswerService(AnswerRepository answerRepository, QuestionRepository questionRepository, QuizRepository quizRepository, RestTemplate restTemplate) {
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
        this.quizRepository = quizRepository;
        this.restTemplate = restTemplate;
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

    public QA_Response_Wrapper getQuestionsAndAnswersByQuizId(UUID id) {
        List<QA_Response> qa_response = new ArrayList<>();
        List<Question> questionList = questionRepository.findAllByQuizId(id);
        Optional<Quiz> pQuiz = quizRepository.getQuizById(id);
        Quiz quiz = new Quiz();
        if (pQuiz.isPresent()) {
            quiz = pQuiz.get();
        } else {
            throw new BadRequestException("Quiz does not exist!");
        }

        String correctAnswer = "";

        Person person = null;

        if (quiz.getPersonId() != null) {
            try {
                person = restTemplate.getForObject(
                        "http://person-service/api/persons?id=" + quiz.getPersonId().toString(),
                        Person.class
                );
            } catch (ResourceAccessException exception) {
                registerEvent(EventRequest.actionType.GET, "/api/answers/quiz", "503");
                throw new ServiceUnavailableException("Error while communicating with another microservice.");
            }
        }

        for (Question q : questionList) {
            List<Answer> answerList = answerRepository.findAllByQuestionId(q.getId());
            ArrayList<String> incorrectAnswers = new ArrayList<>();
            for (Answer a : answerList) {
                if (a.getCorrect()) correctAnswer = a.getName();
                else incorrectAnswers.add(a.getName());
            }
            ArrayList<String> options = (ArrayList<String>) incorrectAnswers.clone();
            options.add(correctAnswer);
            qa_response.add(new QA_Response(q.getId().toString(), quiz.getCategory() != null ? quiz.getCategory().getName() : null, "multiple", q.getName(), correctAnswer, incorrectAnswers, options));
        }
        return new QA_Response_Wrapper(qa_response, quiz, person);
    }
}
