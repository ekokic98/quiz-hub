package com.quizhub.property.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.quizhub.property.model.Person;
import com.quizhub.property.repositories.PersonRepository;
import org.junit.BeforeClass;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.ResultMatcher.matchAll;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PersonControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Order(1)
    @Test
    public void testAddPerson() throws Exception {
        Person p1 = new Person(null, "Bauman", null);
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(p1);
        mockMvc.perform(post("/api/property-service/persons").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isOk()).andDo(print());
    }

    @Order(2)
    @Test
    public void testGetPersonByUsername() throws Exception {
        mockMvc.perform(get("/api/property-service/persons/username").param("username", "Bauman")).andExpect(matchAll(status().isOk(),
                jsonPath("$.username", hasToString("Bauman")))).andDo(print());
    }

    @Order(3)
    @Test
    public void testFailGetPersonByUsername() throws Exception {
        this.mockMvc.perform(get("/api/property-service/persons/username").param("username", "Jypth")).andExpect(status().is4xxClientError()).andDo(print());
    }

    @Order(4)
    @Test
    public void testGetAllPersons() throws Exception {
        this.mockMvc.perform(get("/api/property-service/persons/all")).andExpect(matchAll(status().isOk(),
                jsonPath("$.*", hasSize(4)))).andDo(print());
    }
/*
    @Order(1)
    @Test
    public void testGetAllPersonsA() throws Exception {
                ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(pers.get(0));
        Person p1 = new Person(null, "Bauer", null);

               mockMvc.perform(post("/api/property-service/persons").contentType(MediaType.APPLICATION_JSON).content)
        this.mockMvc.perform(get("/api/property-service/persons/all")).andExpect(matchAll(status().isOk(),
                jsonPath("$.*", hasSize(3)))).andDo(print());
    } */
}
