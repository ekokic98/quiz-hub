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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
                .post("/api/quiz-ms/quizzes")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"person\": {\n" +
                        "        \"id\": \"d234091b-41f8-45a5-927a-89f88e6d5da0\",\n" +
                        "        \"firstName\": \"asd\",\n" +
                        "        \"lastName\": \"sdasda\",\n" +
                        "        \"email\": \"asd@gmail.com\",\n" +
                        "        \"userName\": \"asdasdasd\",\n" +
                        "        \"password\": \"asdaklsjdlk\"\n" +
                        "    },\n" +
                        "    \"category\": {\n" +
                        "        \"id\": \"d22558ce-d8e7-400c-b37f-a6de88192fe1\",\n" +
                        "        \"name\": \"Quiz 280\",\n" +
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
        JSONObject jsonPerson = jsonObject.getJSONObject("person");
        String addedPersonId = jsonPerson.getString("id");
        String addedPersonEmail = jsonPerson.getString("email");
        String addedPersonFirstName = jsonPerson.getString("firstName");
        String addedPersonLastName = jsonPerson.getString("lastName");

        ResponseEntity<String> response = restTemplate.getForEntity("http://person-service/api/person-ms/persons?id=" + addedPersonId, String.class);
        Assert.assertNotNull(response.getBody());

        JSONObject jsonResponse = new JSONObject(response.getBody());
        Assert.assertEquals(jsonResponse.getString("id"), addedPersonId);
        Assert.assertEquals(jsonResponse.getString("email"), addedPersonEmail);
        Assert.assertEquals(jsonResponse.getString("firstName"), addedPersonFirstName);
        Assert.assertEquals(jsonResponse.getString("lastName"), addedPersonLastName);
    }

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
                .post("/api/quiz-ms/quizzes")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"person\": {\n" +
                        "        \"id\": \"d234091b-41f8-45a5-927a-89f88e6d5da0\",\n" +
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
