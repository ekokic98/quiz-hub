package com.quizhub.property.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.quizhub.property.model.*;
import com.quizhub.property.repositories.*;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;


import java.time.LocalDateTime;
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

    private List<Person> persons;
    private List<Quiz> quizzes;
    private List<Comment> comments;
    private ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();


    @BeforeAll
    public void init(@Autowired PersonRepository pR, @Autowired QuizRepository qR, @Autowired CommentRepository cR) {

        Person  p1 = new Person(null, "Balmr", null),
                p2 = new Person(null, "Palmeer", null),
                p3 = new Person(null, "Desssdler", null);
         Quiz   q1 = new Quiz(null, p1, "RPR quiz ff", 300, 5),
                q2 = new Quiz(null, p1, "DM quiz ff2", 600, 10),
                q3 = new Quiz(null, p2, "PNWT quiz f1", 200, 3);
        Comment c1 = new Comment(null, p2, q2, "Wow, nice quiz! ff", null, null),
                c2 = new Comment(null, p1, q1, "Easy ;) ff", null, null);

        persons = Arrays.asList(p1, p2, p3); quizzes = Arrays.asList(q1, q2, q3); comments = Arrays.asList(c1, c2);
        pR.saveAll(persons); qR.saveAll(quizzes); cR.saveAll(comments);
    }

    @Order(1)
    @Test
    public void testAddComment () throws  Exception {
        Comment c1 = new Comment(null, persons.get(0), quizzes.get(0), "Testing my comment", null, null);
        String json = ow.writeValueAsString(c1);
        mockMvc.perform(post("/api/property-service/comments").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isOk()).andDo(print());
    }

    @Order(2)
    @Test
    public void testFailAddCommentWithInvalidData () throws  Exception {
        Person p= new Person(UUID.randomUUID(), "Mauschen", null);  //person does not exist in database
        Comment c1 = new Comment(null, p, quizzes.get(0), "Testing my comment 2", null, null);
        String json = ow.writeValueAsString(c1);
        mockMvc.perform(post("/api/property-service/comments").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isBadRequest()).andDo(print());
    }

    @Order(3)
    @Test
    public void testGetAllComments() throws Exception {
        // should contain 3 comments since we added one in previous tests
        this.mockMvc.perform(get("/api/property-service/comments/all")).andExpect(matchAll(status().isOk(),
                jsonPath("$.*", hasSize(3)))).andDo(print());
    }

    @Order(4)
    @Test
    public void testUpdateComment() throws Exception {
        Comment c = comments.get(0);
        c.setContent("Update comment test");
        //localdatetime causes weird json parsing pattern so dates are nulled
        c.setDateCreated(null); c.setDateUpdated(null);
        String json = ow.writeValueAsString(comments.get(0));
        mockMvc.perform(put("/api/property-service/comments").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isOk());
    }

    @Order(5)
    @Test
    public void testFailUpdateUnexistingComment() throws Exception {
        Comment c1 = new Comment(null, persons.get(0), quizzes.get(0), "Fake comment", null, null);
        String json = ow.writeValueAsString(c1);
        mockMvc.perform(put("/api/property-service/comments").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isBadRequest()).andDo(print());
    }

    @Order(6)
    @Test
    public void testGetComment() throws Exception {
        // passing id of comment that we just updated and checking if contains "Update test" in content field
        this.mockMvc.perform(get("/api/property-service/comments").param("id", comments.get(0).getId().toString()))
                .andExpect(matchAll(status().isOk(), jsonPath("$.content", is("Update comment test")))).andDo(print());
    }

    @Order(7)
    @Test
    public void testFailGetUnexistingComment() throws Exception {
        this.mockMvc.perform(get("/api/property-service/comments").param("id", UUID.randomUUID().toString()))
                .andExpect(status().isBadRequest()).andDo(print());
    }

    @Order(8)
    @Test
    public void testDeleteComment() throws Exception {
        // deleting comment and checking if comment was deleted (there should be only 2 comments in db)
        this.mockMvc.perform(delete("/api/property-service/comments").param("id", comments.get(0).getId().toString()))
                .andExpect(status().isOk()).andDo(mvcResult -> mockMvc.perform(get("/api/property-service/comments/all"))
                .andExpect(matchAll(status().isOk(), jsonPath("$.*", hasSize(2)))));
    }

    @Order(9)
    @Test
    public void testFailDeleteRemovedComment() throws Exception {
        this.mockMvc.perform(delete("/api/property-service/comments").param("username",comments.get(0).getId().toString())).andExpect(status().isBadRequest());
    }

    @Order(10)
    @Test
    public void testCommentContentValidation() throws Exception {
        String stringWith300Chars = new String(new char[300]).replace('\0', 'x');
        Comment c1 = new Comment(null, persons.get(0), quizzes.get(0), stringWith300Chars, null, null);
        String json = ow.writeValueAsString(c1);
        mockMvc.perform(post("/api/property-service/comments").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isBadRequest()).andDo(print());
    }

    @Order(11)
    @Test
    public void testCommentNotNullValidation() throws Exception {
        // randomly testing field with not-null property by passing null as argument
        Comment c1 = new Comment(null, null, quizzes.get(0), null, null, null);
        String json = ow.writeValueAsString(c1);
        mockMvc.perform(post("/api/property-service/comments").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isBadRequest()).andDo(print());
    }

}