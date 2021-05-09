package com.quizhub.tournament.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Tournament {

    @Id
    @Type(type = "uuid-char")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime dateCreated;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime dateStart;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime dateEnd;

    @NotBlank
    @NotNull
    @Column(nullable = false, unique = true)
    @Size(max = 255)
    private String name;

    public Tournament() {
    }

    public Tournament(String id) {
        this.id = UUID.fromString(id);
    }

    public Tournament(UUID id, LocalDateTime dateCreated, LocalDateTime dateStart, LocalDateTime dateEnd, String name) {
        this.id = id;
        this.dateCreated = dateCreated;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public LocalDateTime getDateStart() {
        return dateStart;
    }

    public void setDateStart(LocalDateTime dateStart) {
        this.dateStart = dateStart;
    }

    public LocalDateTime getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(LocalDateTime dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
