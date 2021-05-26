package com.quizhub.person.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UpdateRequest {

    @NotBlank(message = "First name can't be blank")
    @Size(min = 2, max = 50, message = "First name must contain between 2 and 50 characters")
    private String firstName;

    @NotBlank(message = "Last name can't be blank")
    @Size(min = 2, max = 50, message = "Last name must contain between 2 and 50 characters")
    private String lastName;

    @NotBlank(message = "Email can't be blank")
    @Email(message = "Email format must be valid")
    @Size(max = 320, message = "Email can't be longer than 320 characters")
    private String email;

    @NotBlank(message = "Username can't be blank")
    private String username;

    private String city;


    public UpdateRequest() {
    }

    public UpdateRequest(String firstName, String lastName, String email, String username, String city) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.city = city;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
