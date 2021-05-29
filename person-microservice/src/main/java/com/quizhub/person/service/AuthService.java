package com.quizhub.person.service;

import com.quizhub.person.exception.BadRequestException;
import com.quizhub.person.exception.ConflictException;
import com.quizhub.person.model.Person;
import com.quizhub.person.model.PersonFollower;
import com.quizhub.person.model.Role;
import com.quizhub.person.repository.PersonFollowerRepository;
import com.quizhub.person.repository.PersonRepository;
import com.quizhub.person.request.LoginRequest;
import com.quizhub.person.request.SignupRequest;
import com.quizhub.person.request.UpdateRequest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AuthService {

    private final PersonRepository personRepository;
    private final PersonFollowerRepository personFollowerRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(PersonRepository personRepository, PersonFollowerRepository personFollowerRepository, PasswordEncoder passwordEncoder) {
        this.personRepository = personRepository;
        this.personFollowerRepository = personFollowerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Person signup(SignupRequest signupRequest) {
        if (personRepository.existsByUsernameIgnoreCase(signupRequest.getUsername())) {
            throw new ConflictException("Username is already taken");
        }
        if (personRepository.existsByEmail(signupRequest.getEmail())) {
            throw new ConflictException("Email is already taken");
        }
        Person p = new Person(
                signupRequest.getFirstName(),
                signupRequest.getLastName(),
                signupRequest.getEmail(),
                signupRequest.getUsername(),
                passwordEncoder.encode(signupRequest.getPassword()));
        // postavka role
        p.setRoles(Role.ROLE_USER);
        Person person = personRepository.save(p);
        person.setPassword(null);
        return person;
    }

    public Person updateProfile(UpdateRequest updateProfileRequest) {
        Person person = personRepository.findByUsername(updateProfileRequest.getUsername())
                .orElseThrow(() -> new BadRequestException("Username doesn't exist"));

        /*
        if (!person.getId().equals(personId)) {
            throw new UnauthorizedException("You are not allowed to edit someone else's profile");
        }
         */

        if (personRepository.existsByEmail(updateProfileRequest.getEmail())) {
            throw new ConflictException("Email is already taken");
        }

        person.setFirstName(updateProfileRequest.getFirstName());
        person.setLastName(updateProfileRequest.getLastName());
        person.setEmail(updateProfileRequest.getEmail());
        person.setCity(updateProfileRequest.getCity());

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

    public List<PersonFollower> getFollows(UUID id) {
        return personFollowerRepository.findByPersonId(id);
    }
}
