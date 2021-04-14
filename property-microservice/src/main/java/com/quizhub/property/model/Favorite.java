package com.quizhub.property.model;

import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class Favorite {
    @Id
    @Type(type = "uuid-char")
    private UUID id;

    @Type(type = "uuid-char")
    private UUID person;

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
