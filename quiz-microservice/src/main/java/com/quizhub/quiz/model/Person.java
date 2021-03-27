package com.quizhub.quiz.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.URL;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.UUID;

@Entity
public class Person {
    @Id
    @Type(type = "uuid-char")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column(nullable = false)
    @NotBlank
    @Size(min = 2, max = 50)
    private String firstName;

    @Column(nullable = false)
    @NotBlank
    @Size(min = 2, max = 50)
    private String lastName;

    @Column
    @URL
    @Size(max = 255)
    private String imageUrl;

    @NotBlank
    @Column(nullable = false, unique = true)
    @Size(max = 255)
    private String userName;

    @Email(message = "Email should be valid")
    @NotBlank
    @Size(max = 320)
    @Column(nullable = false, unique = true)
    private String email;

    public Person() {
    }

    public Person(UUID id, String firstName, String lastName, String imageUrl, @NotEmpty String userName, @Email(message = "Email should be valid") @NotEmpty String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.imageUrl = imageUrl;
        this.userName = userName;
        this.email = email;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
