package com.quizhub.property.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Score {
    @Id
    @Type(type = "uuid-char")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;

    @ManyToOne
    @JoinColumn(name="quiz_id", nullable = false)
    private Quiz quiz;

    @Column(nullable = false)
    @Min(value = 0, message = "Minimal value for total time is 0")
    private int totalTime;

    @Column(nullable = false)
    @Min(value = 0, message = "Minimal value for nr of answers is 0")
    private int correctAnswers;

    @Column(nullable = false)
    @Min(value = 0, message = "Minimal value for nr of points is 0")
    private int points;

    @Column(nullable = false)
    private LocalDateTime dateScored;

    public Score() {
    }

    public Score(UUID id, Person person, Quiz quiz, int totalTime, int correctAnswers, int points, LocalDateTime dateScored) {
        this.id = id;
        this.quiz = quiz;
        this.person = person;
        this.totalTime = totalTime;
        this.correctAnswers = correctAnswers;
        this.points = points;
        this.dateScored = dateScored;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public int getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(int totalTime) {
        this.totalTime = totalTime;
    }

    public int getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers(int correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public LocalDateTime getDateScored() {
        return dateScored;
    }

    public void setDateScored(LocalDateTime dateScored) {
        this.dateScored = dateScored;
    }
}
