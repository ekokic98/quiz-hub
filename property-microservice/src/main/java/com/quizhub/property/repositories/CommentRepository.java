package com.quizhub.property.repositories;

import com.quizhub.property.model.Comment;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface CommentRepository extends CrudRepository<Comment, UUID> {
}
