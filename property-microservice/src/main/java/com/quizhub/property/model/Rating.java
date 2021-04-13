package com.quizhub.property.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.UUID;

@Entity
public class Rating {
    @Id
    @Type(type = "uuid-char")
    private UUID id;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false)
    @JsonIgnoreProperties({"username", "imageUrl"})
    private Person person;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne
    @JoinColumn(name="quiz_id", nullable = false)
    @JsonIgnoreProperties({"person", "name", "timeLimit", "totalQuestions"})
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

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }
}
