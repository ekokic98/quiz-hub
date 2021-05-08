package com.quizhub.person.security;

import com.quizhub.person.model.Role;
import org.springframework.security.core.GrantedAuthority;
import com.quizhub.person.model.Person;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;

public class PersonDetails implements UserDetails {

    private final Person person;
    private Collection<? extends GrantedAuthority> authorities;

    public PersonDetails(Person person) {
        this.person = person;
    }

    public PersonDetails(Person person, Collection<? extends GrantedAuthority> authorities) {
        this.person = person;
        this.authorities = authorities;
    }

    public static PersonDetails build (Person person) {
        return new PersonDetails(person, person.fetchAuthorities());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public UUID getId() {
        return person.getId();
    }

    @Override
    public String getPassword() {
        return person.getPassword();
    }

    @Override
    public String getUsername() {
        return person.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        PersonDetails user = (PersonDetails) o;
        return Objects.equals(getId(), user.getId());
    }
}
