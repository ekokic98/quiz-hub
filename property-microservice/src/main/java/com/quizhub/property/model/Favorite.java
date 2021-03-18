package com.quizhub.property.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class Favorite {
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

    public Favorite() {
    }

    public Favorite(UUID id, Quiz quiz, Person person) {
        this.id = id;
        this.quiz = quiz;
        this.person = person;
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

    public void setUserId(Person personId) {
        this.person = personId;
    }
}
