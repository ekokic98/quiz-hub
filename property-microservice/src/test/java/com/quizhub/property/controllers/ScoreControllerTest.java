package com.quizhub.property.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.quizhub.property.model.Comment;
import com.quizhub.property.model.Score;
import com.quizhub.property.repositories.CommentRepository;
import com.quizhub.property.repositories.ScoreRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.ResultMatcher.matchAll;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ScoreControllerTest {
    @Autowired
    private MockMvc mockMvc;

    private List<Score> scores;
    private ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();


    @BeforeAll
    public void init(@Autowired ScoreRepository sR) {
        //2 scores already in db (via data.sql)
        Score s1 = new Score(UUID.randomUUID(), UUID.fromString("4c50e7ec-8754-48d4-a2fb-bf3045901340"), UUID.fromString("f1e252f0-737e-4fb0-87a8-2cd23a18b4f9"), 120, 5, 15, null);
        scores = Arrays.asList(s1);
        sR.saveAll(scores);
    }

    @Order(1)
    @Test
    public void testAddScore () throws  Exception {
        Score s1 = new Score(UUID.randomUUID(), UUID.fromString("d234091b-41f8-45a5-927a-89f88e6d5da0"), UUID.fromString("f1e252f0-737e-4fb0-87a8-2cd23a18b4f9"), 120, 5, 15, null);
        String json = ow.writeValueAsString(s1);
        mockMvc.perform(post("/api/property-service/scores").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isOk()).andDo(print());
    }

    @Order(2)
    @Test
    public void testFailAddScoreWithInvalidData () throws  Exception {
        //quiz does not exist in database
        Score s1 = new Score(UUID.randomUUID(), UUID.fromString("4c50e7ec-8754-48d4-a2fb-bf3045901340"), UUID.fromString("9c99e9ec-8754-48d4-a2fb-bf3045901340"), 120, 5, 15, null);
        String json = ow.writeValueAsString(s1);
        mockMvc.perform(post("/api/property-service/scores").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isBadRequest()).andDo(print());
    }

    @Order(3)
    @Test
    public void testGetAllScores() throws Exception {
        // should contain 4 scores since we added one in previous tests
        this.mockMvc.perform(get("/api/property-service/scores/all")).andExpect(matchAll(status().isOk(),
                jsonPath("$.*", hasSize(4)))).andDo(print());
    }

    @Order(4)
    @Test
    public void testGetScore() throws Exception {
        this.mockMvc.perform(get("/api/property-service/scores").param("id", scores.get(0).getId().toString()))
                .andExpect(status().isOk());
    }

    @Order(5)
    @Test
    public void testFailGetUnexistingScore() throws Exception {
        this.mockMvc.perform(get("/api/property-service/scores").param("id", UUID.randomUUID().toString()))
                .andExpect(status().isBadRequest()).andDo(print());
    }

    @Order(6)
    @Test
    public void testDeleteScore() throws Exception {
        // deleting score and checking if score was deleted (there should be only 3 scores in db)
        this.mockMvc.perform(delete("/api/property-service/scores").param("id", scores.get(0).getId().toString()))
                .andExpect(status().isOk()).andDo(mvcResult -> mockMvc.perform(get("/api/property-service/scores/all"))
                .andExpect(matchAll(status().isOk(), jsonPath("$.*", hasSize(3)))));
    }

    @Order(7)
    @Test
    public void testFailDeleteRemovedScore() throws Exception {
        this.mockMvc.perform(delete("/api/property-service/scores").param("username", scores.get(0).getId().toString())).andExpect(status().isBadRequest());
    }

    @Order(8)
    @Test
    public void testScoreIntegerTypeAttributesValidation() throws Exception {
        // totalTime, correctAnswers and points have same validation tags, it's sufficient to test with one integer attribute
        Score s1 = new Score(UUID.randomUUID(), UUID.fromString("4c50e7ec-8754-48d4-a2fb-bf3045901340"), UUID.fromString("f1e252f0-737e-4fb0-87a8-2cd23a18b4f9"), -120, 5, 15, null);
        String json = ow.writeValueAsString(s1);
        mockMvc.perform(post("/api/property-service/scores").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isBadRequest()).andDo(print());
    }

    @Order(9)
    @Test
    public void testScoreNotNullValidation() throws Exception {
        Score s1 = new Score(null, null, null, -120, 5, 15, null);
        String json = ow.writeValueAsString(s1);
        mockMvc.perform(post("/api/property-service/scores").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isBadRequest()).andDo(print());
    }

    @Order(10)
    @Test
    public void testGetScoreByUsername() throws Exception {
        // person with id d72d5d78-97d7-11eb-a8b3-0242ac130003 is Ben5
        this.mockMvc.perform(get("/api/property-service/scores/all/user").param("username", "John5"))
                .andExpect(status().isOk());
    }

    @Order(11)
    @Test
    public void testFailGetScoreByUnexistingUsername() throws Exception {
        // person with id d72d5d78-97d7-11eb-a8b3-0242ac130003 is Anna5
        this.mockMvc.perform(get("/api/property-service/scores/all/user").param("username", "Fake name"))
                .andExpect(status().is4xxClientError()).andDo(print());
    }

    @Order(12)
    @Test
    public void testGetScoreByQuiz() throws Exception {
        // person with id d72d5d78-97d7-11eb-a8b3-0242ac130003 is Ben5
        this.mockMvc.perform(get("/api/property-service/scores/all/quiz").param("id", "debb8e83-54ba-4320-b0a1-29779fc54648"))
                .andExpect(status().isOk());
    }


}
