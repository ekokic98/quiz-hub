package com.quizhub.quiz.response;

import com.quizhub.quiz.model.Quiz;

import java.util.List;

public class UpdateQuizModel {
   private List<QA_Response> qa_response;
   private QuizResponse quiz;

    public UpdateQuizModel(List<QA_Response> qa_response, QuizResponse quiz) {
        this.qa_response = qa_response;
        this.quiz = quiz;
    }

    public UpdateQuizModel(List<QA_Response> qa_response, Quiz quiz) {
        this.qa_response = qa_response;
        this.quiz = new QuizResponse(quiz);
    }

    public UpdateQuizModel() {
    }

    public List<QA_Response> getQa_response() {
        return qa_response;
    }

    public void setQa_response(List<QA_Response> qa_response) {
        this.qa_response = qa_response;
    }

    public QuizResponse getQuiz() {
        return quiz;
    }

    public void setQuiz(QuizResponse quiz) {
        this.quiz = quiz;
    }
}
