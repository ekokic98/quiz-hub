package com.quizhub.property.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.quizhub.property.model.Rating;
import com.quizhub.property.model.Person;
import com.quizhub.property.model.Quiz;
import com.quizhub.property.repositories.RatingRepository;
import com.quizhub.property.repositories.PersonRepository;
import com.quizhub.property.repositories.QuizRepository;
import com.quizhub.property.repositories.RatingRepository;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RatingControllerTest {
    @Autowired
    private MockMvc mockMvc;

    private List<Person> persons;
    private List<Quiz> quizzes;
    private List<Rating> ratings;
    private ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();


    @BeforeAll
    public void init(@Autowired PersonRepository pR, @Autowired QuizRepository qR, @Autowired RatingRepository rR) {

        Person  p1 = new Person(null, "Baueraa", null),
                p2 = new Person(null, "Palmeraa", null),
                p3 = new Person(null, "Dessleraa", null);
        Quiz   q1 = new Quiz(null, p1, "RPR quizaa", 300, 5),
                q2 = new Quiz(null, p1, "DM quizaa", 600, 10);
        Rating c1 = new Rating(null, p1, q1, 5),
                c2 =  new Rating(null, p2, q2, 2);
        persons = Arrays.asList(p1, p2, p3); quizzes = Arrays.asList(q1, q2); ratings = Arrays.asList(c1, c2);
        pR.saveAll(persons); qR.saveAll(quizzes); rR.saveAll(ratings);
    }

    @Order(1)
    @Test
    public void testAddRating () throws  Exception {
        Rating c1 = new Rating(null, persons.get(2), quizzes.get(1), 5);
        String json = ow.writeValueAsString(c1);
        mockMvc.perform(post("/api/property-service/ratings").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isOk()).andDo(print());
    }

    @Order(2)
    @Test
    public void testFailAddRatingWithInvalidData () throws  Exception {
        Person p= new Person(UUID.randomUUID(), "Mauschen", null);  //person does not exist in database
        Rating c1 = new Rating(null, p, quizzes.get(1), 5);
        String json = ow.writeValueAsString(c1);
        mockMvc.perform(post("/api/property-service/ratings").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isBadRequest()).andDo(print());
    }

    @Order(3)
    @Test
    public void testGetAllRatings() throws Exception {
        // should contain 3 Ratings since we added one in previous tests
        this.mockMvc.perform(get("/api/property-service/ratings/all")).andExpect(matchAll(status().isOk(),
                jsonPath("$.*", hasSize(3)))).andDo(print());
    }

    @Order(4)
    @Test
    public void testUpdateRating() throws Exception {
        Rating c = ratings.get(0);
        c.setRate(3);
        String json = ow.writeValueAsString(ratings.get(0));
        mockMvc.perform(put("/api/property-service/ratings").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isOk());
    }

    @Order(5)
    @Test
    public void testFailUpdateUnexistingRating() throws Exception {
        Rating c1 = new Rating(null, persons.get(2), quizzes.get(1), 5);
        String json = ow.writeValueAsString(c1);
        mockMvc.perform(put("/api/property-service/ratings").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isBadRequest()).andDo(print());
    }

    @Order(6)
    @Test
    public void testGetRating() throws Exception {
        // passing id of Rating that we just updated and checking if contains rate 3 in "rate" field
        this.mockMvc.perform(get("/api/property-service/ratings").param("id", ratings.get(0).getId().toString()))
                .andExpect(matchAll(status().isOk(), jsonPath("$.rate", is(3)))).andDo(print());
    }

    @Order(7)
    @Test
    public void testFailGetUnexistingRating() throws Exception {
        this.mockMvc.perform(get("/api/property-service/ratings").param("id", UUID.randomUUID().toString()))
                .andExpect(status().isBadRequest()).andDo(print());
    }

    @Order(8)
    @Test
    public void testDeleteRating() throws Exception {
        // deleting Rating and checking if Rating was deleted (there should be only 2 Ratings in db)
        this.mockMvc.perform(delete("/api/property-service/ratings").param("id", ratings.get(0).getId().toString()))
                .andExpect(status().isOk()).andDo(mvcResult -> mockMvc.perform(get("/api/property-service/ratings/all"))
                .andExpect(matchAll(status().isOk(), jsonPath("$.*", hasSize(2)))));
    }

    @Order(9)
    @Test
    public void testFailDeleteRemovedRating() throws Exception {
        this.mockMvc.perform(delete("/api/property-service/ratings").param("username", ratings.get(0).getId().toString())).andExpect(status().isBadRequest());
    }

    @Order(10)
    @Test
    public void testRatingContentValidation() throws Exception {
        // rate value below 1 or above 5 is not allowed
        Rating c1 = new Rating(null, persons.get(2), quizzes.get(1), 7);
        Rating c2 = new Rating(null, persons.get(2), quizzes.get(1), -3);
        String json = ow.writeValueAsString(c1);
        String json2 = ow.writeValueAsString(c2);
        mockMvc.perform(post("/api/property-service/ratings").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isBadRequest())
                .andDo(mvcResult -> {mockMvc.perform(post("/api/property-service/ratings").contentType(MediaType.APPLICATION_JSON).content(json2)).andExpect(status().isBadRequest());});
    }

    @Order(11)
    @Test
    public void testRatingNotNullValidation() throws Exception {
        // randomly testing field with not-null property by passing null as argument
        Rating c1 = new Rating(null, null, quizzes.get(1), 1);
        String json = ow.writeValueAsString(c1);
        mockMvc.perform(post("/api/property-service/ratings").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isBadRequest()).andDo(print());
    }

    @Order(12)
    @Test
    public void testFailAddSameRating () throws  Exception {
        Rating c1 = new Rating(null, persons.get(2), quizzes.get(1), 5);
        String json = ow.writeValueAsString(c1);
        mockMvc.perform(post("/api/property-service/ratings").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().is4xxClientError()).andDo(print());
    }

}
