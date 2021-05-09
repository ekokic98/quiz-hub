package com.quizhub.tournament.model;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.URL;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.UUID;

@Entity
public class Person {
    @Id
    @Type(type = "uuid-char")
    private UUID id;

    @URL
    @Size(max = 255)
    private String imageUrl;

    @Size(max = 255)
    private String username;

    public Person() {
    }

    public Person(String id) {
        this.id = UUID.fromString(id);
    }

    public Person(UUID id, @URL String imageUrl, @NotBlank String username) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.username = username;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
