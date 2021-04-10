package com.quizhub.quiz.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class Tournament {
    private UUID id;
    private LocalDateTime dateCreated;
    private LocalDateTime dateStart;
    private LocalDateTime dateEnd;
    private String name;

    public Tournament() {
    }

    public Tournament(UUID id, LocalDateTime dateCreated, LocalDateTime dateStart, LocalDateTime dateEnd, String name) {
        this.id = id;
        this.dateCreated = dateCreated;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public LocalDateTime getDateStart() {
        return dateStart;
    }

    public void setDateStart(LocalDateTime dateStart) {
        this.dateStart = dateStart;
    }

    public LocalDateTime getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(LocalDateTime dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
