package com.quizhub.quiz.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class QuizControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void addQuizCommunicationTest() throws Exception {

        RequestBuilder request = MockMvcRequestBuilders
                .post("/api/quizzes")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"personId\": \"d234091b-41f8-45a5-927a-89f88e6d5da0\",\n" +
                        "    \"category\": {\n" +
                        "        \"id\": \"d22558ce-d8e7-400c-b37f-a6de88192fe1\",\n" +
                        "        \"name\": \"Quizz 0000\",\n" +
                        "        \"imageUrl\": null\n" +
                        "    },\n" +
                        "    \"name\": \"Quizz 0000\",\n" +
                        "    \"dateCreated\": \"2020-11-29T15:50:05.555\",\n" +
                        "    \"timeLimit\": 23,\n" +
                        "    \"totalQuestions\": 10\n" +
                        "}");

        MvcResult mvcResult = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo("Quizz 0000")))
                .andExpect(jsonPath("$.totalQuestions", equalTo(10)))
                .andExpect(jsonPath("$.timeLimit", equalTo(23)))
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        JSONObject jsonObject = new JSONObject(content);
        String addedPersonId = jsonObject.getString("personId");

        ResponseEntity<String> response = restTemplate.getForEntity("http://person-service/api/persons?id=" + addedPersonId, String.class);
        Assert.assertNotNull(response.getBody());

        JSONObject jsonResponse = new JSONObject(response.getBody());
        Assert.assertEquals(jsonResponse.getString("id"), addedPersonId);
    }

    @Test
    public void getAllQuizzesTest() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .get("/api/quizzes/all")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void getQuizTest() throws Exception {
        String id = (String) addQuiz("Quiz 1").get("id");

        RequestBuilder request = MockMvcRequestBuilders
                .get("/api/quizzes")
                .param("id", id)
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(id)))
                .andReturn();
    }
    private Map addQuiz(String name) throws Exception {
        RequestBuilder postRequest = MockMvcRequestBuilders
                .post("/api/quizzes")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"personId\": \"d234091b-41f8-45a5-927a-89f88e6d5da0\",\n" +
                        "    \"category\": {\n" +
                        "        \"id\": \"d22558ce-d8e7-400c-b37f-a6de88192fe1\",\n" +
                        "        \"name\": \"Quizgg\",\n" +
                        "        \"imageUrl\": null\n" +
                        "    },\n" +
                        "    \"name\": \"Quizbbb\",\n" +
                        "    \"dateCreated\": \"2020-11-29T15:50:05.555\",\n" +
                        "    \"timeLimit\": 23,\n" +
                        "    \"totalQuestions\": 10\n" +
                        "}");

        MvcResult mvcResult = mockMvc.perform(postRequest)
                .andReturn();

        String json = mvcResult.getResponse().getContentAsString();
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, Map.class);
    }

    @Test
    public void getQuizzesForTournamentTest() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .get("/api/quizzes/tournament")
                .param("id", "112e4019-c5ec-49a2-9be4-4871dcebe89f")
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("[]"))
                .andReturn();
    }
}
