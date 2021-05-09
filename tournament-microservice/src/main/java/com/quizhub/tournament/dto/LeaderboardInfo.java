package com.quizhub.tournament.dto;

import com.quizhub.tournament.model.Person;
import com.quizhub.tournament.model.Score;
import com.quizhub.tournament.model.Quiz;

public class LeaderboardInfo {
    private Person person;
    private Quiz quiz;
    private Score score;

    public LeaderboardInfo() {
    }

    public LeaderboardInfo(Person person, Quiz quiz, Score score) {
        this.person = person;
        this.quiz = quiz;
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

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }
}
