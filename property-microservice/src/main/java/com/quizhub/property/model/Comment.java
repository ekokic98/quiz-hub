package com.quizhub.property.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import org.hibernate.annotations.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME;

@Entity
public class Comment {
    @Id
    @Type(type = "uuid-char")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne
    @JoinColumn(name="quiz_id", nullable = false)
    private Quiz quiz;

    @Column(nullable = false)
    @NotBlank
    @Size(min = 2, max = 255, message = "Comment can't be shorter than 2 chars or longer than 255")
    private String content;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime dateCreated;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime dateUpdated;

    public Comment() {
    }

    public Comment(UUID id, Person person, Quiz quiz, String content, LocalDateTime dateCreated, LocalDateTime dateUpdated) {
        this.id = id;
        this.quiz = quiz;
        this.person = person;
        this.content = content;
        this.dateCreated = dateCreated;
        this.dateUpdated = dateUpdated;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Quiz getQuiz() { return quiz; }

    public void setQuiz(Quiz quiz) { this.quiz = quiz; }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public LocalDateTime getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(LocalDateTime dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", person=" + person +
                ", quiz=" + quiz +
                ", content='" + content + '\'' +
                ", dateCreated=" + dateCreated +
                ", dateUpdated=" + dateUpdated +
                '}';
    }


}
