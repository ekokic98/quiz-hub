package com.quizhub.tournament.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Score {
    @Id
    @Type(type = "uuid-char")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "quiz_id", nullable = false)
    private Quiz quiz;

    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;

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
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime dateScored;

    public Score() {
    }

    public Score(UUID id, Quiz quiz, Person person, @Min(value = 0, message = "Minimal value for total time is 0") int totalTime, @Min(value = 0, message = "Minimal value for nr of answers is 0") int correctAnswers, @Min(value = 0, message = "Minimal value for nr of points is 0") int points, LocalDateTime dateScored) {
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
