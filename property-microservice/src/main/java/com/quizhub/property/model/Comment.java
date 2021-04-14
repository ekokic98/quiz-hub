package com.quizhub.property.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Comment {
    @Id
    @Type(type = "uuid-char")
    private UUID id;

    @Column(name="person_id", nullable = false)
    @Type(type = "uuid-char")
    private UUID person;

    @Column(name="quiz_id", nullable = false)
    @Type(type = "uuid-char")
    private UUID quiz;

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

    public Comment(UUID id, UUID person, UUID quiz, String content, LocalDateTime dateCreated, LocalDateTime dateUpdated) {
        this.id = id;
        this.quiz = quiz;
        this.person = person;
        this.content = content;
        this.dateCreated = dateCreated;
        this.dateUpdated = dateUpdated;
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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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
