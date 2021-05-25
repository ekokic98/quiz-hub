package com.quizhub.quiz.response;

import com.quizhub.quiz.model.Quiz;

import java.util.List;

public class QA_Response_Wrapper {
    List<QA_Response> qa_response;
    Quiz quiz;

    public QA_Response_Wrapper(List<QA_Response> qa_response, Quiz quiz) {
        this.qa_response = qa_response;
        this.quiz = quiz;
    }

    public List<QA_Response> getQa_response() {
        return qa_response;
    }

    public void setQa_response(List<QA_Response> qa_response) {
        this.qa_response = qa_response;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }
}
