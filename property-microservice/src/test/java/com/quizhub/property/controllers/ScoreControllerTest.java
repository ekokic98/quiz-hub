package com.quizhub.property.controllers;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ScoreControllerTest {
/*    @Autowired
    private MockMvc mockMvc;

    private List<Person> persons;
    private List<Quiz> quizzes;
    private List<Score> scores;
    private ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();


    @BeforeAll
    public void init(@Autowired PersonRepository pR, @Autowired QuizRepository qR, @Autowired ScoreRepository sR) {

        Person  p1 = new Person(null, "Bauerqq", null),
                p2 = new Person(null, "Palmerqq", null),
                p3 = new Person(null, "Desslerqq", null);
        Quiz   q1 = new Quiz(null, p1, "RPR quizqq", 300, 5),
                q2 = new Quiz(null, p1, "DM quizqq", 600, 10),
                q3 = new Quiz(null, p2, "PNWT quizqq", 200, 3);
        Score s1 = new Score(null, p1, q1, 120, 5, 15, null),
                s2 = new Score(null, p1, q2, 200, 7, 5, null);

        persons = Arrays.asList(p1, p2, p3); quizzes = Arrays.asList(q1, q2, q3); scores = Arrays.asList(s1, s2);
        pR.saveAll(persons); qR.saveAll(quizzes); sR.saveAll(scores);
    }

    @Order(1)
    @Test
    public void testAddScore () throws  Exception {
        Score s1 = new Score(null, persons.get(0), quizzes.get(0), 120, 5, 15, null);
        String json = ow.writeValueAsString(s1);
        mockMvc.perform(post("/api/property-service/scores").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isOk()).andDo(print());
    }

    @Order(2)
    @Test
    public void testFailAddScoreWithInvalidData () throws  Exception {
        Quiz q=  new Quiz(UUID.randomUUID(), persons.get(0), "RPR quiz", 300, 5);  //quiz does not exist in database
        Score s1 = new Score(null, persons.get(0), q, 120, 5, 15, null);
        String json = ow.writeValueAsString(s1);
        mockMvc.perform(post("/api/property-service/scores").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isBadRequest()).andDo(print());
    }

    @Order(3)
    @Test
    public void testGetAllScores() throws Exception {
        // should contain 3 scores since we added one in previous tests
        this.mockMvc.perform(get("/api/property-service/scores/all")).andExpect(matchAll(status().isOk(),
                jsonPath("$.*", hasSize(3)))).andDo(print());
    }

    @Order(4)
    @Test
    public void testGetScore() throws Exception {
        this.mockMvc.perform(get("/api/property-service/scores").param("id", scores.get(0).getId().toString()))
                .andExpect(status().isOk());
    }

    @Order(5)
    @Test
    public void testFailGetUnexistingScore() throws Exception {
        this.mockMvc.perform(get("/api/property-service/scores").param("id", UUID.randomUUID().toString()))
                .andExpect(status().isBadRequest()).andDo(print());
    }

    @Order(6)
    @Test
    public void testDeleteScore() throws Exception {
        // deleting score and checking if score was deleted (there should be only 2 comments in db)
        this.mockMvc.perform(delete("/api/property-service/scores").param("id", scores.get(0).getId().toString()))
                .andExpect(status().isOk()).andDo(mvcResult -> mockMvc.perform(get("/api/property-service/scores/all"))
                .andExpect(matchAll(status().isOk(), jsonPath("$.*", hasSize(2)))));
    }

    @Order(7)
    @Test
    public void testFailDeleteRemovedScore() throws Exception {
        this.mockMvc.perform(delete("/api/property-service/scores").param("username", scores.get(0).getId().toString())).andExpect(status().isBadRequest());
    }

    @Order(8)
    @Test
    public void testScoreIntegerTypeAttributesValidation() throws Exception {
        // totalTime, correctAnswers and points have same validation tags, it's sufficient to test with one integer attribute
        Score s1 = new Score(null, persons.get(0), quizzes.get(0), -120, 5, 15, null);
        String json = ow.writeValueAsString(s1);
        mockMvc.perform(post("/api/property-service/scores").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isBadRequest()).andDo(print());
    }

    @Order(9)
    @Test
    public void testScoreNotNullValidation() throws Exception {
        Score s1 = new Score(null, null, null, -120, 5, 15, null);
        String json = ow.writeValueAsString(s1);
        mockMvc.perform(post("/api/property-service/scores").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isBadRequest()).andDo(print());
    }

    @AfterAll
    public void clearDatabase(@Autowired PersonRepository pR) {
        pR.deleteAll();
    }
*/

}
