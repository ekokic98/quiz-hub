package com.quizhub.property.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.quizhub.property.model.Quiz;
import com.quizhub.property.model.Person;
import com.quizhub.property.model.Quiz;
import com.quizhub.property.repositories.QuizRepository;
import com.quizhub.property.repositories.PersonRepository;
import com.quizhub.property.repositories.QuizRepository;
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
public class QuizControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private List<Person> persons;
    private List<Quiz> quizzes;
    private ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();


    @BeforeAll
    public void init(@Autowired PersonRepository pR, @Autowired QuizRepository qR) {

        Person  p1 = new Person(null, "Adon", null),
                p2 = new Person(null, "Doppler", null),
                p3 = new Person(null, "Mark", null);
        Quiz    q1 = new Quiz(null, p1, "RPR quiz", 300, 5),
                q2 = new Quiz(null, p1, "DM quiz", 600, 10);

        persons = Arrays.asList(p1, p2, p3); quizzes = Arrays.asList(q1, q2);
        pR.saveAll(persons); qR.saveAll(quizzes);
    }

    @Order(1)
    @Test
    public void testAddQuiz () throws  Exception {
        Quiz c1 = new Quiz(null, persons.get(0), "Quiz", 300, 5);
        String json = ow.writeValueAsString(c1);
        mockMvc.perform(post("/api/property-service/quizzes").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isOk()).andDo(print());
    }

    @Order(2)
    @Test
    public void testFailAddQuizWithInvalidData () throws  Exception {
        Person p= new Person(UUID.randomUUID(), "Mauschen", null);  //person does not exist in database
        Quiz c1 = new Quiz(null, p, "Quiz", 300, 5);
        String json = ow.writeValueAsString(c1);
        mockMvc.perform(post("/api/property-service/quizzes").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isBadRequest()).andDo(print());
    }

    @Order(3)
    @Test
    public void testGetAllQuizzes() throws Exception {
        // should contain 3 quizzes since we added one in previous tests
        this.mockMvc.perform(get("/api/property-service/quizzes/all")).andExpect(matchAll(status().isOk(),
                jsonPath("$.*", hasSize(3)))).andDo(print());
    }

    @Order(4)
    @Test
    public void testUpdateQuiz() throws Exception {
        Quiz c = quizzes.get(0);
        c.setName("Update test");
        String json = ow.writeValueAsString(quizzes.get(0));
        mockMvc.perform(put("/api/property-service/quizzes").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isOk());
    }

    @Order(5)
    @Test
    public void testFailUpdateUnexistingQuiz() throws Exception {
        Quiz c1 = new Quiz(null, persons.get(0), "Fake Quiz", 300, 5);
        String json = ow.writeValueAsString(c1);
        mockMvc.perform(put("/api/property-service/quizzes").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isBadRequest()).andDo(print());
    }

    @Order(6)
    @Test
    public void testGetQuiz() throws Exception {
        // passing id of Quiz that we just updated and checking if contains "Update test" in content field
        this.mockMvc.perform(get("/api/property-service/quizzes").param("id", quizzes.get(0).getId().toString()))
                .andExpect(matchAll(status().isOk(), jsonPath("$.name", is("Update test")))).andDo(print());
    }

    @Order(7)
    @Test
    public void testFailGetUnexistingQuiz() throws Exception {
        this.mockMvc.perform(get("/api/property-service/quizzes").param("id", UUID.randomUUID().toString()))
                .andExpect(status().isBadRequest()).andDo(print());
    }

    @Order(8)
    @Test
    public void testDeleteQuiz() throws Exception {
        // deleting Quiz and checking if Quiz was deleted (there should be only 2 quizzes in db)
        this.mockMvc.perform(delete("/api/property-service/quizzes").param("id", quizzes.get(0).getId().toString()))
                .andExpect(status().isOk()).andDo(mvcResult -> mockMvc.perform(get("/api/property-service/quizzes/all"))
                .andExpect(matchAll(status().isOk(), jsonPath("$.*", hasSize(2)))));
    }

    @Order(9)
    @Test
    public void testFailDeleteRemovedQuiz() throws Exception {
        this.mockMvc.perform(delete("/api/property-service/quizzes").param("username",quizzes.get(0).getId().toString())).andExpect(status().isBadRequest());
    }

    @Order(10)
    @Test
    public void testQuizIntegerTypeAttributesValidation() throws Exception {
        Quiz c1 = new Quiz(null, persons.get(0), "Negative total question quiz", 300, -5);
        String json = ow.writeValueAsString(c1);
        mockMvc.perform(post("/api/property-service/quizzes").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isBadRequest()).andDo(print());
    }

    @Order(11)
    @Test
    public void testQuizNotNullValidation() throws Exception {
        // testing not null fields with null passed argument
        Quiz c1 = new Quiz(null, persons.get(0), null, 300, 5);
        String json = ow.writeValueAsString(c1);
        mockMvc.perform(post("/api/property-service/quizzes").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isBadRequest()).andDo(print());
    }

}
