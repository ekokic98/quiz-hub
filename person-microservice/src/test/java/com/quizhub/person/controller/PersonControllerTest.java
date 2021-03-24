package com.quizhub.person.controller;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getPersons() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .get("/api/person-service/persons/all")
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void getPersonByUsername() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .get("/api/person-service/persons?username=username")
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    public void getPersonByid() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .get("/api/person-service/persons?id=id123")
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    public void addPerson() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .post("/api/person-service/persons")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"city\": \"\",\n" +
                        "  \"country\": \"\",\n" +
                        "  \"dateCreated\": \"2021-03-24T11:08:46.299Z\",\n" +
                        "  \"email\": \"john.doe@gmail.com\",\n" +
                        "  \"firstName\": \"John\",\n" +
                        "  \"id\": \"3fa85f64-5717-4562-b3fc-2c963f66afa6\",\n" +
                        "  \"imageUrl\": \"\",\n" +
                        "  \"lastName\": \"Doe\",\n" +
                        "  \"password\": \"John12!\",\n" +
                        "  \"username\": \"John5\"\n" +
                        "}");

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void addPersonInvalidEmail() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .post("/api/person-service/persons")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"city\": \"\",\n" +
                        "  \"country\": \"\",\n" +
                        "  \"dateCreated\": \"2021-03-24T11:08:46.299Z\",\n" +
                        "  \"email\": \"john.com\",\n" +
                        "  \"firstName\": \"John\",\n" +
                        "  \"id\": \"3fa85f64-5717-4562-b3fc-2c963f66afa6\",\n" +
                        "  \"imageUrl\": \"\",\n" +
                        "  \"lastName\": \"Doe\",\n" +
                        "  \"password\": \"John12!\",\n" +
                        "  \"username\": \"John5\"\n" +
                        "}");

        mockMvc.perform(request)
                .andExpect(status().isBadRequest())
                .andReturn();
    }
}
