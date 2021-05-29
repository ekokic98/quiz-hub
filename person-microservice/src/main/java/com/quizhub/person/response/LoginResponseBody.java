package com.quizhub.person.response;

import com.quizhub.person.model.Person;
import com.quizhub.person.model.PersonFollower;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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
    private List<String> roles;
    private List<PersonFollower> follows;

    public LoginResponseBody() {
    }

    public LoginResponseBody(String tokenType, String jwt, UUID id, String city, String country, LocalDateTime dateCreated, String imageUrl, String firstName, String lastName, String email, String username, List<String> roles, List<PersonFollower> follows) {
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
        this.follows = follows;
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

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
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

    public List<PersonFollower> getFollows() {
        return follows;
    }

    public void setFollows(List<PersonFollower> follows) {
        this.follows = follows;
    }
}
