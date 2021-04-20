package com.quizhub.property.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.quizhub.property.model.Favorite;
import com.quizhub.property.repositories.FavoriteRepository;
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
public class FavoriteControllerTest {
    @Autowired
    private MockMvc mockMvc;

    private List<Favorite> favorites;
    private ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();


    @BeforeAll
    public void init(@Autowired FavoriteRepository fR) {
        fR.deleteAll();
        Favorite s1 = new Favorite(UUID.randomUUID(),UUID.fromString("debb8e83-54ba-4320-b0a1-29779fc54648"), UUID.fromString("d234091b-41f8-45a5-927a-89f88e6d5da0")),
                s2 = new Favorite(UUID.randomUUID(), UUID.fromString("debb8e83-54ba-4320-b0a1-29779fc54648"), UUID.fromString("b8181463-a15f-4eda-9d3b-e0e7ce2559a6"));
        favorites = Arrays.asList(s1, s2);
        fR.saveAll(favorites);
    }

    @Order(1)
    @Test
    public void testAddFavorite () throws  Exception {
        Favorite s1 = new Favorite(UUID.randomUUID(),  UUID.fromString("f1e252f0-737e-4fb0-87a8-2cd23a18b4f9"), UUID.fromString("d72d5d78-97d7-11eb-a8b3-0242ac130003"));
        String json = ow.writeValueAsString(s1);
        mockMvc.perform(post("/api/property-ms/favorites").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isOk()).andDo(print());
    }

    @Order(2)
    @Test
    public void testFailAddFavoriteWithInvalidData () throws  Exception {
        Favorite s1 = new Favorite(UUID.randomUUID(),UUID.fromString("debb8e82-54ba-4320-b0a1-29779fc54638"), UUID.fromString("debb8e82-54ba-4320-b0a1-29779fc54638"));
        String json = ow.writeValueAsString(s1);
        mockMvc.perform(post("/api/property-ms/favorites").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isBadRequest()).andDo(print());
    }

    @Order(3)
    @Test
    public void testGetAllfavorites() throws Exception {
        // should contain 3 favorites since we added one in previous tests
        this.mockMvc.perform(get("/api/property-ms/favorites/all")).andExpect(matchAll(status().isOk(),
                jsonPath("$.*", hasSize(3)))).andDo(print());
    }

    @Order(4)
    @Test
    public void testGetFavorite() throws Exception {
        this.mockMvc.perform(get("/api/property-ms/favorites").param("id", favorites.get(0).getId().toString()))
                .andExpect(status().isOk());
    }

    @Order(5)
    @Test
    public void testFailGetUnexistingFavorite() throws Exception {
        this.mockMvc.perform(get("/api/property-ms/favorites").param("id", UUID.randomUUID().toString()))
                .andExpect(status().isBadRequest()).andDo(print());
    }

    @Order(6)
    @Test
    public void testDeleteFavorite() throws Exception {
        // deleting Favorite and checking if Favorite was deleted (there should be only 2 comments in db)
        this.mockMvc.perform(delete("/api/property-ms/favorites").param("id", favorites.get(0).getId().toString()))
                .andExpect(status().isOk()).andDo(mvcResult -> mockMvc.perform(get("/api/property-ms/favorites/all"))
                .andExpect(matchAll(status().isOk(), jsonPath("$.*", hasSize(2)))));
    }

    @Order(7)
    @Test
    public void testFailDeleteRemovedFavorite() throws Exception {
        this.mockMvc.perform(delete("/api/property-ms/favorites").param("id", favorites.get(0).getId().toString())).andExpect(status().isBadRequest());
    }


    @Order(8)
    @Test
    public void testFavoriteNotNullValidation() throws Exception {
        Favorite s1 = new Favorite(UUID.randomUUID(), UUID.randomUUID(), null);
        String json = ow.writeValueAsString(s1);
        mockMvc.perform(post("/api/property-ms/favorites").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isBadRequest()).andDo(print());
    }

    @Order(9)
    @Test
    public void testFailAddExistingFavorite () throws  Exception {
        Favorite s1 = new Favorite(UUID.randomUUID(), UUID.fromString("d234091b-41f8-45a5-927a-89f88e6d5da0"),UUID.fromString("debb8e83-54ba-4320-b0a1-29779fc54648"));
        String json = ow.writeValueAsString(s1);
        mockMvc.perform(post("/api/property-ms/favorites").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().is4xxClientError()).andDo(print());
    }

    @Order(10)
    @Test
    public void testGetFavoriteByUsername() throws Exception {
        // person with id d72d5d78-97d7-11eb-a8b3-0242ac130003 is Anna5
        this.mockMvc.perform(get("/api/property-ms/favorites/all/user").param("username", "Anna5"))
                .andExpect(status().isOk());
    }







}
