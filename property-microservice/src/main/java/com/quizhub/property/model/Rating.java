package com.quizhub.property.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
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

    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;

    @ManyToOne
    @JoinColumn(name="quiz_id", nullable = false)
    private Quiz quiz;

    @Column(nullable = false)
    @Min(value = 1, message = "Minimal value is 1")
    @Max(value = 5, message = "Max value is 5")
    private int rate;

    public Rating() {
    }

    public Rating(UUID id, Person person, Quiz quiz, int rate) {
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

    public Quiz getQuizId() {
        return quiz;
    }

    public void setQuizId(Quiz quizId) {
        this.quiz = quizId;
    }

    public Person getUserId() {
        return person;
    }

    public void setUserId(Person person) {
        this.person = person;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }
}
