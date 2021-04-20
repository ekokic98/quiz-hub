package com.quizhub.property.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.quizhub.property.model.Rating;
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

    private List<Rating> ratings;
    private ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();


    @BeforeAll
    public void init(@Autowired RatingRepository rR) {
        Rating c1 = new Rating(UUID.randomUUID(), UUID.fromString("d72d5d78-97d7-11eb-a8b3-0242ac130003"), UUID.fromString("debb8e83-54ba-4320-b0a1-29779fc54648"), 3);
        ratings = Arrays.asList(c1);
        rR.saveAll(ratings);
    }

    @Order(1)
    @Test
    public void testAddRating () throws  Exception {
        Rating c1 = new Rating(UUID.randomUUID(), UUID.fromString("b8181463-a15f-4eda-9d3b-e0e7ce2559a6"), UUID.fromString("a545e6a4-546e-45ad-880f-81bfda328b01"), 3);
        String json = ow.writeValueAsString(c1);
        mockMvc.perform(post("/api/property-ms/ratings").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isOk()).andDo(print());
    }

    @Order(2)
    @Test
    public void testFailAddRatingWithInvalidData () throws  Exception {
        //unexisting quiz/person id
        Rating c1 = new Rating(UUID.randomUUID(), UUID.fromString("b8888888-a15f-4eda-9d3b-e0e7ce2559a6"), UUID.fromString("debb8e88-88ba-4320-b0a1-29779fc54648"), 5);
        String json = ow.writeValueAsString(c1);
        mockMvc.perform(post("/api/property-ms/ratings").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isBadRequest()).andDo(print());
    }

    @Order(3)
    @Test
    public void testGetAllRatings() throws Exception {
        // should contain 4 Ratings since we added 2 in previous tests + 2 are already in base (data.sql)
        this.mockMvc.perform(get("/api/property-ms/ratings/all")).andExpect(matchAll(status().isOk(),
                jsonPath("$.*", hasSize(4)))).andDo(print());
    }

    @Order(4)
    @Test
    public void testUpdateRating() throws Exception {
        Rating c = ratings.get(0);
        c.setRate(3);
        String json = ow.writeValueAsString(ratings.get(0));
        mockMvc.perform(put("/api/property-ms/ratings").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isOk());
    }

    @Order(5)
    @Test
    public void testFailUpdateUnexistingRating() throws Exception {
        Rating c1 = new Rating(UUID.randomUUID(),UUID.fromString("b8888888-a15f-4eda-9d3b-e0e7ce2559a6"), UUID.fromString("debb8e88-88ba-4320-b0a1-29779fc54648"), 5);
        String json = ow.writeValueAsString(c1);
        mockMvc.perform(put("/api/property-ms/ratings").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isBadRequest()).andDo(print());
    }

    @Order(6)
    @Test
    public void testGetRating() throws Exception {
        // passing id of Rating that we just updated and checking if contains rate 3 in "rate" field
        this.mockMvc.perform(get("/api/property-ms/ratings").param("id", ratings.get(0).getId().toString()))
                .andExpect(matchAll(status().isOk(), jsonPath("$.rate", is(3)))).andDo(print());
    }

    @Order(7)
    @Test
    public void testFailGetUnexistingRating() throws Exception {
        this.mockMvc.perform(get("/api/property-ms/ratings").param("id", UUID.randomUUID().toString()))
                .andExpect(status().isBadRequest()).andDo(print());
    }

    @Order(8)
    @Test
    public void testDeleteRating() throws Exception {
        // deleting Rating and checking if Rating was deleted (there should be only 3 Ratings in db)
        this.mockMvc.perform(delete("/api/property-ms/ratings").param("id", ratings.get(0).getId().toString()))
                .andExpect(status().isOk()).andDo(mvcResult -> mockMvc.perform(get("/api/property-ms/ratings/all"))
                .andExpect(matchAll(status().isOk(), jsonPath("$.*", hasSize(3)))));
    }

    @Order(9)
    @Test
    public void testFailDeleteRemovedRating() throws Exception {
        this.mockMvc.perform(delete("/api/property-ms/ratings").param("username", ratings.get(0).getId().toString())).andExpect(status().isBadRequest());
    }

    @Order(10)
    @Test
    public void testRatingContentValidation() throws Exception {
        // rate value below 1 or above 5 is not allowed
        Rating c1 = new Rating(UUID.randomUUID(), UUID.fromString("d234091b-41f8-45a5-927a-89f88e6d5da0"), UUID.fromString("debb8e83-54ba-4320-b0a1-29779fc54648"), 7);
        Rating c2 = new Rating(UUID.randomUUID(), UUID.fromString("d234091b-41f8-45a5-927a-89f88e6d5da0"), UUID.fromString("debb8e83-54ba-4320-b0a1-29779fc54648"), -3);
        String json = ow.writeValueAsString(c1);
        String json2 = ow.writeValueAsString(c2);
        mockMvc.perform(post("/api/property-ms/ratings").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isBadRequest())
                .andDo(mvcResult -> {mockMvc.perform(post("/api/property-ms/ratings").contentType(MediaType.APPLICATION_JSON).content(json2)).andExpect(status().isBadRequest());});
    }

    @Order(11)
    @Test
    public void testRatingNotNullValidation() throws Exception {
        // randomly testing field with not-null property by passing null as argument
        Rating c1 = new Rating(UUID.randomUUID(), null, UUID.fromString("debb8e83-54ba-4320-b0a1-29779fc54648"), 1);
        String json = ow.writeValueAsString(c1);
        mockMvc.perform(post("/api/property-ms/ratings").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isBadRequest()).andDo(print());
    }

    @Order(12)
    @Test
    public void testFailAddSameRating () throws  Exception {
        Rating c1 = new Rating(UUID.randomUUID(),UUID.fromString("b8888888-a15f-4eda-9d3b-e0e7ce2559a6"), UUID.fromString("debb8e88-88ba-4320-b0a1-29779fc54648"), 5);
        String json = ow.writeValueAsString(c1);
        mockMvc.perform(post("/api/property-ms/ratings").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().is4xxClientError()).andDo(print());
    }

    @Order(13)
    @Test
    public void testGetFavoriteByUsername() throws Exception {
        // person with id d72d5d78-97d7-11eb-a8b3-0242ac130003 is Anna5
        this.mockMvc.perform(get("/api/property-ms/ratings/all/user").param("username", "Anna5"))
                .andExpect(status().isOk());
    }

}
