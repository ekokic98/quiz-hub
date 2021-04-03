package com.quizhub.quiz.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Map;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class QuizControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAllQuizzesTest() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .get("/api/quiz-service/quizzes/all")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void getQuizTest() throws Exception {
        String id = (String) addQuiz("Quiz 1").get("id");

        RequestBuilder request = MockMvcRequestBuilders
                .get("/api/quiz-service/quizzes")
                .param("id", id)
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(id)))
                .andReturn();
    }
    private Map addQuiz(String name) throws Exception {
        RequestBuilder postRequest = MockMvcRequestBuilders
                .post("/api/quiz-service/quizzes")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"person\": {\n" +
                        "        \"id\": \"41faa4ec-e7eb-4741-83e8-1697b454929c\",\n" +
                        "        \"email\": \"amus@etf.ba\",\n" +
                        "        \"firstName\": \"Amra\",\n" +
                        "        \"lastName\": \"Music\",\n" +
                        "        \"userName\": \"amusic\"\n" +
                        "    },\n" +
                        "    \"category\": {\n" +
                        "        \"id\": \"cbca14b8-17ab-4fe7-a343-9ab65c142cc6\",\n" +
                        "        \"name\": \"Category\",\n" +
                        "        \"imageUrl\": null\n" +
                        "    },\n" +
                        "    \"name\": \"Quiz 112\",\n" +
                        "    \"dateCreated\": \"2020-11-29T15:50:05.609\",\n" +
                        "    \"timeLimit\": 23,\n" +
                        "    \"totalQuestions\": 10\n" +
                        "}");

        MvcResult mvcResult = mockMvc.perform(postRequest)
                .andReturn();

        String json = mvcResult.getResponse().getContentAsString();
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, Map.class);
    }
}
