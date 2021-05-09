package com.quizhub.property.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class Favorite {
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


    public Favorite() {
    }

    public Favorite(UUID id, UUID quiz, UUID person) {
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
