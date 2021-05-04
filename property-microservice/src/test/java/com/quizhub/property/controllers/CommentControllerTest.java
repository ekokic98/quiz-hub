package com.quizhub.property.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.quizhub.property.dto.Person;
import com.quizhub.property.dto.Quiz;
import com.quizhub.property.model.*;
import com.quizhub.property.repositories.*;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import java.util.Arrays;
import java.util.List;
import java.util.UUID;


import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.ResultMatcher.matchAll;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CommentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private List<Comment> comments;
    private ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();


    @BeforeAll
    public void init(@Autowired CommentRepository cR) {
        // 2 comments already in base
        Comment c1 = new Comment(UUID.randomUUID(), UUID.fromString("d234091b-41f8-45a5-927a-89f88e6d5da0"),UUID.fromString("debb8e83-54ba-4320-b0a1-29779fc54648"), "Wow, nice quiz! ff", null, null);
        comments = Arrays.asList(c1);
        cR.save(c1);
    }

    @Order(1)
    @Test
    public void testAddComment () throws  Exception {
        Comment c1 = new Comment(UUID.randomUUID(), UUID.fromString("b8181463-a15f-4eda-9d3b-e0e7ce2559a6"), UUID.fromString("debb8e83-54ba-4320-b0a1-29779fc54648"), "Testing my comment", null, null);
        String json = ow.writeValueAsString(c1);
        mockMvc.perform(post("/api/comments").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isOk()).andDo(print());
    }

    @Order(2)
    @Test
    public void testFailAddCommentWithInvalidData () throws  Exception {
        // unexisting person/quiz id
        Comment c1 = new Comment(UUID.randomUUID(), UUID.fromString("f8181463-a15f-4eda-9d3b-e0e7ce2559a5"), UUID.fromString("febb8e83-54ba-4320-b0a1-29779fc54348"), "Testing my comment 2", null, null);
        String json = ow.writeValueAsString(c1);
        mockMvc.perform(post("/api/comments").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isBadRequest()).andDo(print());
    }

    @Order(3)
    @Test
    public void testGetAllComments() throws Exception {
        // should contain 4 comments since we added one in previous tests
        this.mockMvc.perform(get("/api/comments/all")).andExpect(matchAll(status().isOk(),
                jsonPath("$.*", hasSize(4)))).andDo(print());
    }

    @Order(4)
    @Test
    public void testUpdateComment() throws Exception {
        Comment c = comments.get(0);
        c.setContent("Update comment test");
        //localdatetime causes weird json parsing pattern so dates are nulled
        c.setDateCreated(null); c.setDateUpdated(null);
        String json = ow.writeValueAsString(comments.get(0));
        mockMvc.perform(put("/api/comments").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isOk());
    }

    @Order(5)
    @Test
    public void testFailUpdateUnexistingComment() throws Exception {
        Comment c1 = new Comment(UUID.randomUUID(),UUID.fromString("f8181463-a15f-4eda-9d3b-e0e7ce2559a5"), UUID.fromString("febb8e83-54ba-4320-b0a1-29779fc54348"), "Fake comment", null, null);
        String json = ow.writeValueAsString(c1);
        mockMvc.perform(put("/api/comments").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isBadRequest()).andDo(print());
    }

    @Order(6)
    @Test
    public void testGetComment() throws Exception {
        // passing id of comment that we just updated and checking if contains "Update test" in content field
        this.mockMvc.perform(get("/api/comments").param("id", comments.get(0).getId().toString()))
                .andExpect(matchAll(status().isOk(), jsonPath("$.content", is("Update comment test")))).andDo(print());
    }

    @Order(7)
    @Test
    public void testFailGetUnexistingComment() throws Exception {
        this.mockMvc.perform(get("/api/comments").param("id", UUID.randomUUID().toString()))
                .andExpect(status().isBadRequest()).andDo(print());
    }

    @Order(8)
    @Test
    public void testDeleteComment() throws Exception {
        // deleting comment and checking if comment was deleted (there should be only 2 comments in db)
        this.mockMvc.perform(delete("/api/comments").param("id", comments.get(0).getId().toString()))
                .andExpect(status().isOk()).andDo(mvcResult -> mockMvc.perform(get("/api/comments/all"))
                .andExpect(matchAll(status().isOk(), jsonPath("$.*", hasSize(3)))));
    }

    @Order(9)
    @Test
    public void testFailDeleteRemovedComment() throws Exception {
        this.mockMvc.perform(delete("/api/comments").param("username",comments.get(0).getId().toString())).andExpect(status().isBadRequest());
    }

    @Order(10)
    @Test
    public void testCommentContentValidation() throws Exception {
        String stringWith300Chars = new String(new char[300]).replace('\0', 'x');
        Comment c1 = new Comment(null, UUID.fromString("b8181463-a15f-4eda-9d3b-e0e7ce2559a6"), UUID.fromString("debb8e83-54ba-4320-b0a1-29779fc54648"), stringWith300Chars, null, null);
        String json = ow.writeValueAsString(c1);
        mockMvc.perform(post("/api/comments").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isBadRequest()).andDo(print());
    }

    @Order(11)
    @Test
    public void testCommentNotNullValidation() throws Exception {
        // randomly testing field with not-null property by passing null as argument
        Comment c1 = new Comment(UUID.randomUUID(), null, UUID.fromString("debb8e83-54ba-4320-b0a1-29779fc54648"), null, null, null);
        String json = ow.writeValueAsString(c1);
        mockMvc.perform(post("/api/comments").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isBadRequest()).andDo(print());
    }


}
