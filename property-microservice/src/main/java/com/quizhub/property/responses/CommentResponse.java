package com.quizhub.property.responses;

import com.quizhub.property.dto.Person;
import com.quizhub.property.model.Comment;

public class CommentResponse {
    private Comment comment;
    private Person person;

    public CommentResponse(Comment comment, Person person) {
        this.comment = comment;
        this.person = person;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
