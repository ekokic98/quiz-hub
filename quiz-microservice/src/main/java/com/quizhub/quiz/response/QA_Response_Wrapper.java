package com.quizhub.quiz.response;

import com.quizhub.quiz.dto.Person;
import com.quizhub.quiz.model.Quiz;

import java.util.List;

public class QA_Response_Wrapper {
    List<QA_Response> qa_response;
    Quiz quiz;
    Person person;

    public QA_Response_Wrapper(List<QA_Response> qa_response, Quiz quiz, Person person) {
        this.qa_response = qa_response;
        this.quiz = quiz;
        this.person = person;
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

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
