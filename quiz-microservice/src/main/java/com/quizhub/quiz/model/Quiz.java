package com.quizhub.quiz.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Quiz {
    @Id
    @Type(type = "uuid-char")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Type(type = "uuid-char")
    private UUID personId;

    @Type(type = "uuid-char")
    private UUID tournamentId;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;


    @Column(nullable = false, unique = true)
    @NotBlank
    @Size(min = 2, max = 50)
    private String name;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime dateCreated;

    @Min(value = 0, message = "Time limit should not be below 0")
    private int timeLimit;

    @Column(nullable = false)
    @Min(value = 1, message = "Each quiz should contain at least one question")
    private int totalQuestions;

    public Quiz() {
    }

    public Quiz(UUID id, UUID personId, Category category, String name, UUID tournamentId, LocalDateTime dateCreated, int timeLimit, int totalQuestions) {
        this.id = id;
        this.personId = personId;
        this.tournamentId = tournamentId;
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

    public UUID getPersonId() {
        return personId;
    }

    public void setPersonId(UUID personId) {
        this.personId = personId;
    }

    public UUID getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(UUID tournamentId) {
        this.tournamentId = tournamentId;
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
