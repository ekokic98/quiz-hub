package com.quizhub.property.repositories;

import com.quizhub.property.model.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CommentRepository extends CrudRepository<Comment, UUID> {
    boolean existsById(UUID id);

    List<Comment> findAllByQuizOrderByDateCreatedDesc(UUID quiz);
}
