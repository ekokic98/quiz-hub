package com.quizhub.quiz.response;

import com.quizhub.quiz.model.Quiz;

import java.time.LocalDateTime;
import java.util.UUID;

public class QuizResponse {
    private UUID id;
    private UUID personId;
    private UUID tournamentId;
    private UUID categoryId;
    private String name;
    private LocalDateTime dateCreated;
    private Integer timeLimit;
    private Integer totalQuestions;

    public QuizResponse(UUID id, UUID personId, UUID tournamentId, UUID categoryId, String name, LocalDateTime dateCreated, Integer timeLimit, Integer totalQuestions) {
        this.id = id;
        this.personId = personId;
        this.tournamentId = tournamentId;
        this.categoryId = categoryId;
        this.name = name;
        this.dateCreated = dateCreated;
        this.timeLimit = timeLimit;
        this.totalQuestions = totalQuestions;
    }

    public QuizResponse() {
    }

    public QuizResponse(Quiz quiz) {
        this.id = quiz.getId();
        this.personId = quiz.getPersonId();
        this.tournamentId = quiz.getTournamentId();
        this.categoryId = quiz.getCategory().getId();
        this.name = quiz.getName();
        this.dateCreated = quiz.getDateCreated();
        this.timeLimit = quiz.getTimeLimit();
        this.totalQuestions = quiz.getTotalQuestions();

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }


    public UUID getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(UUID tournamentId) {
        this.tournamentId = tournamentId;
    }

    public UUID getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(UUID categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }


    public Integer getTotalQuestions() {
        return totalQuestions;
    }

    public void setTotalQuestions(Integer totalQuestions) {
        this.totalQuestions = totalQuestions;
    }

    public UUID getPersonId() {
        return personId;
    }

    public void setPersonId(UUID personId) {
        this.personId = personId;
    }

    public Integer getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(Integer timeLimit) {
        this.timeLimit = timeLimit;
    }
}
