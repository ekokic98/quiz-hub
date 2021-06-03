package com.quizhub.tournament.responses;

import com.quizhub.tournament.model.Person;
import com.quizhub.tournament.model.Quiz;
import com.quizhub.tournament.model.Score;

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
