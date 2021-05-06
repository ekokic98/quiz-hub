package com.quizhub.person.response;

import com.quizhub.person.model.Person;

import java.util.UUID;

public class LoginResponse {

    private String jwt;
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String username;

    public LoginResponse() {
    }

    public LoginResponse(String jwt, UUID id, String firstName, String lastName, String email, String username) {
        this.jwt = jwt;
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
