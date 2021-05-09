package com.quizhub.property.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.UUID;

@Entity
public class Rating {
    @Id
    @Type(type = "uuid-char")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column(name="person_id", nullable = false)
    @Type(type = "uuid-char")
    private UUID person;

    @Column(name="quiz_id", nullable = false)
    @Type(type = "uuid-char")
    private UUID quiz;

    @Column(nullable = false)
    @Min(value = 1, message = "Minimal value is 1")
    @Max(value = 5, message = "Max value is 5")
    private int rate;

    public Rating() {
    }

    public Rating(UUID id,UUID person, UUID quiz, int rate) {
        this.id = id;
        this.person = person;
        this.quiz = quiz;
        this.rate = rate;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public UUID getPerson() {
        return person;
    }

    public void setPerson(UUID person) {
        this.person = person;
    }

    public UUID getQuiz() {
        return quiz;
    }

    public void setQuiz(UUID quiz) {
        this.quiz = quiz;
    }
}
