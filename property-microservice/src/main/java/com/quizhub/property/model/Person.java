package com.quizhub.property.model;

import io.swagger.v3.oas.annotations.links.Link;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import org.hibernate.validator.constraints.URL;
import org.hibernate.validator.internal.constraintvalidators.hv.URLValidator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.UUID;

@Entity
public class Person {

    @Id
    @Type(type = "uuid-char")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column(nullable = false, unique = true)
    @NotBlank
    @Size(min = 2, max = 20)
    private String username;

    private String imageUrl;

    public Person() {
    }

    public Person(UUID id, String username, String imageUrl) {
        this.id = id;
        this.username = username;
        this.imageUrl = imageUrl;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
