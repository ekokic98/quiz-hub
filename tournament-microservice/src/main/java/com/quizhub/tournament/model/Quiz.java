package com.quizhub.tournament.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.Type;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class Quiz {
    @Id
    @Type(type = "uuid-char")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "tournament_id")
    @JsonProperty(value = "tournamentId")
    private Tournament tournament;

    @NotBlank
    @Size(min = 2, max = 50)
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Min(value = 1, message = "Each quiz should contain at least one question")
    private int totalQuestions;

    public Quiz() {
    }

    public Quiz(String id) {
        this.id = UUID.fromString(id);
    }

    public Quiz(UUID id, Tournament tournament, @Size(min = 2, max = 50) @NotBlank String name, @Min(value = 1, message = "Each quiz should contain at least one question") int totalQuestions) {
        this.id = id;
        this.tournament = tournament;
        this.name = name;
        this.totalQuestions = totalQuestions;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public void setTotalQuestions(int totalQuestions) {
        this.totalQuestions = totalQuestions;
    }

    public Quiz withTournament(Tournament tournament) {
        return new Quiz(id, tournament, name, totalQuestions);
    }
}
