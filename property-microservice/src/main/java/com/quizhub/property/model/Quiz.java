package com.quizhub.property.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.UUID;

@Entity
public class Quiz {
    @Id
    @Type(type = "uuid-char")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @ManyToOne
    @JoinColumn(name="person_id", nullable = false)
    private Person person;

    @Column(nullable = false)
    @NotBlank
    @Size(min=2, max=50)
    private String name;

    @Min(value = 0, message = "Time limit should not be below 0")
    private int timeLimit;

    @Column(nullable = false)
    @Min(value = 1, message = "Each quiz should contain at least one question")
    private int totalQuestions;

    public Quiz() {
    }

    public Quiz(UUID id, Person person, String name, int timeLimit, int totalQuestions) {
        this.id = id;
        this.name = name;
        this.timeLimit = timeLimit;
        this.totalQuestions = totalQuestions;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Person getUserId() {
        return person;
    }

    public void setUserId(Person personId) {
        this.person = personId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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