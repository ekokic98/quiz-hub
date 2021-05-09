package com.quizhub.tournament.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class Quiz {
    private UUID id;
    private Category category;
    private String name;
    private LocalDateTime dateCreated;
    private int timeLimit;
    private int totalQuestions;

    public Quiz() {
    }

    public Quiz(UUID id, Category category, String name, LocalDateTime dateCreated, int timeLimit, int totalQuestions) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.dateCreated = dateCreated;
        this.timeLimit = timeLimit;
        this.totalQuestions = totalQuestions;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
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

    public int getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(int timeLimit) {
        this.timeLimit = timeLimit;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public void setTotalQuestions(int totalQuestions) {
        this.totalQuestions = totalQuestions;
    }
}
