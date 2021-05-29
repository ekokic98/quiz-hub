package com.quizhub.person.repository;

import com.quizhub.person.model.PersonFollower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PersonFollowerRepository extends JpaRepository<PersonFollower, UUID> {

    List<PersonFollower> findByPersonId(UUID id);
}
