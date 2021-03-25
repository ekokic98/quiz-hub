package com.quizhub.property.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.quizhub.property.model.Person;
import com.quizhub.property.model.Quiz;
import com.quizhub.property.model.Favorite;
import com.quizhub.property.repositories.FavoriteRepository;
import com.quizhub.property.repositories.PersonRepository;
import com.quizhub.property.repositories.QuizRepository;
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

    private List<Person> persons;
    private List<Quiz> quizzes;
    private List<Favorite> favorites;
    private ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();


    @BeforeAll
    public void init(@Autowired PersonRepository pR, @Autowired QuizRepository qR, @Autowired FavoriteRepository fR) {

        Person  p1 = new Person(null, "Reus", null),
                p2 = new Person(null, "Saox", null),
                p3 = new Person(null, "Dekler", null);
        Quiz   q1 = new Quiz(null, p1, "uiz", 300, 5),
                q2 = new Quiz(null, p1, "dDM quiz", 600, 10),
                q3 = new Quiz(null, p2, "sPNWT quiz", 200, 3);
        Favorite s1 = new Favorite(null, q1, p1), s2 = new Favorite(null, q2, p1);

        persons = Arrays.asList(p1, p2, p3); quizzes = Arrays.asList(q1, q2, q3); favorites = Arrays.asList(s1, s2);
        pR.saveAll(persons); qR.saveAll(quizzes); fR.saveAll(favorites);
    }

    @Order(1)
    @Test
    public void testAddFavorite () throws  Exception {
        Favorite s1 = new Favorite(null, quizzes.get(2), persons.get(2));
        String json = ow.writeValueAsString(s1);
        mockMvc.perform(post("/api/property-service/favorites").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isOk()).andDo(print());
    }

    @Order(2)
    @Test
    public void testFailAddFavoriteWithInvalidData () throws  Exception {
        Person  p1 = new Person(UUID.randomUUID(), "Reussen", null); //person does not exist in database
        Favorite s1 = new Favorite(null, quizzes.get(0), p1);
        String json = ow.writeValueAsString(s1);
        mockMvc.perform(post("/api/property-service/favorites").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isBadRequest()).andDo(print());
    }

    @Order(3)
    @Test
    public void testGetAllfavorites() throws Exception {
        // should contain 3 favorites since we added one in previous tests
        this.mockMvc.perform(get("/api/property-service/favorites/all")).andExpect(matchAll(status().isOk(),
                jsonPath("$.*", hasSize(3)))).andDo(print());
    }

    @Order(4)
    @Test
    public void testGetFavorite() throws Exception {
        this.mockMvc.perform(get("/api/property-service/favorites").param("id", favorites.get(0).getId().toString()))
                .andExpect(status().isOk());
    }

    @Order(5)
    @Test
    public void testFailGetUnexistingFavorite() throws Exception {
        this.mockMvc.perform(get("/api/property-service/favorites").param("id", UUID.randomUUID().toString()))
                .andExpect(status().isBadRequest()).andDo(print());
    }

    @Order(6)
    @Test
    public void testDeleteFavorite() throws Exception {
        // deleting Favorite and checking if Favorite was deleted (there should be only 2 comments in db)
        this.mockMvc.perform(delete("/api/property-service/favorites").param("id", favorites.get(0).getId().toString()))
                .andExpect(status().isOk()).andDo(mvcResult -> mockMvc.perform(get("/api/property-service/favorites/all"))
                .andExpect(matchAll(status().isOk(), jsonPath("$.*", hasSize(2)))));
    }

    @Order(7)
    @Test
    public void testFailDeleteRemovedFavorite() throws Exception {
        this.mockMvc.perform(delete("/api/property-service/favorites").param("id", favorites.get(0).getId().toString())).andExpect(status().isBadRequest());
    }


    @Order(8)
    @Test
    public void testFavoriteNotNullValidation() throws Exception {
        Favorite s1 = new Favorite(null, quizzes.get(0), null);
        String json = ow.writeValueAsString(s1);
        mockMvc.perform(post("/api/property-service/favorites").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isBadRequest()).andDo(print());
    }

    @Order(9)
    @Test
    public void testFailAddExistingFavorite () throws  Exception {
        Favorite s1 = new Favorite(null, quizzes.get(2), persons.get(2));
        String json = ow.writeValueAsString(s1);
        mockMvc.perform(post("/api/property-service/favorites").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().is4xxClientError()).andDo(print());
    }


}
