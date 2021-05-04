package com.quizhub.tournament.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.quizhub.tournament.repositories.TournamentRepository;
import org.hamcrest.Matchers;
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
import java.util.UUID;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TournamentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TournamentRepository tournamentRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void addGeneratedQuizToTournamentTest() throws Exception {
        String id = addTournament("Example service communication").get("id");

        RequestBuilder request = MockMvcRequestBuilders
                .post("/api/tournaments/quiz")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"tournamentId\": \"" + id + "\",\n" +
                        "    \"amount\": 10\n" +
                        "}");

        MvcResult mvcResult = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalQuestions", equalTo(10)))
                .andExpect(jsonPath("$.timeLimit", equalTo(150)))
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        JSONObject jsonObject = new JSONObject(content);
        String addedQuizId = jsonObject.getString("id");

        ResponseEntity<String> response = restTemplate.getForEntity("http://quiz-service/api/quizzes?id=" + addedQuizId, String.class);
        Assert.assertNotNull(response.getBody());

        JSONObject jsonResponse = new JSONObject(response.getBody());
        Assert.assertEquals(jsonResponse.getString("id"), addedQuizId);
        Assert.assertEquals(jsonResponse.getString("totalQuestions"), "10");
        Assert.assertEquals(jsonResponse.getString("timeLimit"), "150");
    }

    @Test
    public void addBadRequestTest() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .post("/api/tournaments")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"dateStart\": \"2020-11-29T15:50:05.609\",\n" +
                        "    \"dateEnd\": \"2021-11-29T15:50:05.609\",\n" +
                        "    \"name\": \"\"\n" +
                        "}");

        mockMvc.perform(request)
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.path", equalTo("/api/tournaments")))
                .andExpect(jsonPath("$.message", equalTo("name must not be blank")))
                .andReturn();
    }

    @Test
    public void addBadRequest2Test() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .post("/api/tournaments")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"dateStart\": \"2022-11-29T15:50:05.609\",\n" +
                        "    \"dateEnd\": \"2021-11-29T15:50:05.609\",\n" +
                        "    \"name\": \"Exampleeeee\"\n" +
                        "}");

        mockMvc.perform(request)
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    public void addTest() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .post("/api/tournaments")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"dateStart\": \"2020-11-29T15:50:05.609\",\n" +
                        "    \"dateEnd\": \"2021-11-29T15:50:05.609\",\n" +
                        "    \"name\": \"Example\"\n" +
                        "}");

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.anything()))
                .andExpect(jsonPath("$.name", equalTo("Example")))
                .andReturn();
    }

    @Test
    public void updateTest() throws Exception {
        String id = addTournament("Example 0").get("id");

        RequestBuilder updateRequest = MockMvcRequestBuilders
                .put("/api/tournaments")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"id\": \"" + id + "\",\n" +
                        "    \"dateStart\": \"2020-11-29T15:50:05.609\",\n" +
                        "    \"dateEnd\": \"2022-11-29T15:50:05.609\",\n" +
                        "    \"name\": \"Example Update\"\n" +
                        "}");

        mockMvc.perform(updateRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(tournamentRepository.findById(UUID.fromString(id)).get().getName())))
                .andReturn();
    }

    @Test
    public void getAllTournamentsTest() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .get("/api/tournaments/all")
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void getTournamentTest() throws Exception {
        String id = addTournament("Example 2").get("id");

        RequestBuilder request = MockMvcRequestBuilders
                .get("/api/tournaments")
                .param("id", id)
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(id)))
                .andReturn();
    }

    @Test
    public void getLeaderboardForTournamentTest() throws Exception {
        String id = addTournament("Example 4").get("id");

        RequestBuilder request = MockMvcRequestBuilders
                .get("/api/tournaments/leaderboard")
                .param("id", id)
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("[]"))
                .andReturn();
    }

    @Test
    public void getLeaderboardForTournamentBadRequestTest() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .get("/api/tournaments/leaderboard")
                .param("id", "wrong")
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status", equalTo(400)))
                .andExpect(jsonPath("$.error", equalTo("Bad Request")))
                .andReturn();
    }

    private Map<String, String> addTournament(String name) throws Exception {
        RequestBuilder postRequest = MockMvcRequestBuilders
                .post("/api/tournaments")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"dateStart\": \"2020-11-29T15:50:05.609\",\n" +
                        "    \"dateEnd\": \"2021-11-29T15:50:05.609\",\n" +
                        "    \"name\": \"" + name + "\"\n" +
                        "}");

        MvcResult mvcResult = mockMvc.perform(postRequest)
                .andReturn();

        String json = mvcResult.getResponse().getContentAsString();
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, Map.class);
    }
}
