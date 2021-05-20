package com.quizhub.property.responses;

import com.quizhub.property.dto.Person;
import com.quizhub.property.dto.Quiz;
import com.quizhub.property.model.Score;

public class ScoreResponse {
    private Score score;
    private Person person;
    private Quiz quiz;

    public ScoreResponse() {
    }

    public ScoreResponse(Score score, Person person, Quiz quiz) {
        this.score = score;
        this.person = person;
        this.quiz = quiz;
    }

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }
}
