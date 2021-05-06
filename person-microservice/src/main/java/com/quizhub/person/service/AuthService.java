package com.quizhub.person.service;

import com.quizhub.person.exception.ConflictException;
import com.quizhub.person.model.Person;
import com.quizhub.person.repository.PersonRepository;
import com.quizhub.person.request.LoginRequest;
import com.quizhub.person.request.SignupRequest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final PersonRepository personRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(PersonRepository personRepository, PasswordEncoder passwordEncoder) {
        this.personRepository = personRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Person signup(SignupRequest signupRequest) {
        if (personRepository.existsByUsernameIgnoreCase(signupRequest.getUsername())) {
            throw new ConflictException("Username is already taken");
        }
        if (personRepository.existsByEmail(signupRequest.getEmail())) {
            throw new ConflictException("Email is already taken");
        }
        Person person = personRepository.save(new Person(
                signupRequest.getFirstName(),
                signupRequest.getLastName(),
                signupRequest.getEmail(),
                signupRequest.getUsername(),
                passwordEncoder.encode(signupRequest.getPassword()))
        );
        person.setPassword(null);
        return person;
    }

    public Person login(LoginRequest loginRequest) {
        Person person = personRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + loginRequest.getUsername()));
        if (person == null || !passwordEncoder.matches(loginRequest.getPassword(), person.getPassword())) {
            throw new UsernameNotFoundException("Wrong username or password");
        }
        person.setPassword(null);
        return person;
    }
}
