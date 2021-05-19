package com.quizhub.person.response;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

public class LoginResponseBody {

    private String tokenType;
    private String jwt;
    private UUID id;
    private String city;
    private String country;
    private LocalDateTime dateCreated;
    private String imageUrl;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private ArrayList<String> roles;

    public LoginResponseBody() {
    }

    public LoginResponseBody(String tokenType, String jwt, UUID id, String city, String country, LocalDateTime dateCreated, String imageUrl, String firstName, String lastName, String email, String username, ArrayList<String> roles) {
        this.tokenType = tokenType;
        this.jwt = jwt;
        this.id = id;
        this.city = city;
        this.country = country;
        this.dateCreated = dateCreated;
        this.imageUrl = imageUrl;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.roles = roles;
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

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ArrayList<String> getRoles() {
        return roles;
    }

    public void setRoles(ArrayList<String> roles) {
        this.roles = roles;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
