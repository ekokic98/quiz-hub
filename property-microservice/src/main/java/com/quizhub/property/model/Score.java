package com.quizhub.property.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Score {
    @Id
    @Type(type = "uuid-char")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column(name="quiz_id", nullable = false)
    private int quizId;

    @Column(name="user_id", nullable = false)
    private int userId;

    @Column(nullable = false)
    private int totalTime;

    @Column(nullable = false)
    private int correctAnswers;

    @Column(nullable = false)
    private int points;

    @Column(nullable = false)
    private LocalDateTime dateScored;

    public Score() {
    }

    public Score(UUID id, int quizId, int userId, int totalTime, int correctAnswers, int points, LocalDateTime dateScored) {
        this.id = id;
        this.quizId = quizId;
        this.userId = userId;
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

    public int getQuizId() {
        return quizId;
    }

    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
