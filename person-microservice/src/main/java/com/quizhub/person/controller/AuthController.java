package com.quizhub.person.controller;

import com.quizhub.person.model.Person;
import com.quizhub.person.model.Role;
import com.quizhub.person.request.LoginRequest;
import com.quizhub.person.request.SignupRequest;
import com.quizhub.person.response.LoginResponseBody;
import com.quizhub.person.security.JwtUtils;
import com.quizhub.person.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final JwtUtils jwtTokenUtil;
    private final AuthService personService;

    @Autowired
    public AuthController(JwtUtils jwtTokenUtil, AuthService personService) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.personService = personService;
    }

    @PostMapping("/signup")
    public ResponseEntity<LoginResponseBody> signup(@RequestBody @Valid SignupRequest signupRequest) {
        Person person = personService.signup(signupRequest);
        ArrayList<String> roles = new ArrayList<>();
        roles.add("ROLE_USER");
        String token = jwtTokenUtil.generateToken(person);
        return ResponseEntity.ok().body(new LoginResponseBody(
                "Bearer",
                token,
                person.getId(),
                person.getCity(),
                person.getCountry(),
                person.getDateCreated(),
                person.getImageUrl(),
                person.getFirstName(),
                person.getLastName(),
                person.getEmail(),
                person.getUsername(),
                roles
        ));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseBody> login(@RequestBody @Valid LoginRequest loginRequest) {
        Person person = personService.login(loginRequest);
        String token = jwtTokenUtil.generateToken(person);
        ArrayList<String> roles = new ArrayList<>();
        roles.add(person.getRole().name());
        if (person.getRole() == Role.ROLE_ADMIN) {
            roles.add("ROLE_USER");
        }
        return ResponseEntity.ok().body(new LoginResponseBody(
                "Bearer",
                token,
                person.getId(),
                person.getCity(),
                person.getCountry(),
                person.getDateCreated(),
                person.getImageUrl(),
                person.getFirstName(),
                person.getLastName(),
                person.getEmail(),
                person.getUsername(),
                roles
        ));
    }
}
