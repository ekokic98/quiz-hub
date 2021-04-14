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
public class PersonControllerTest {
/*    @Autowired
    private MockMvc mockMvc;

    private ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
    private List<Person> persons;

    @BeforeAll
    public void init(@Autowired PersonRepository pR) {
        pR.deleteAll();
        Person  p1 = new Person(null, "Bauerg", null),
                p2 = new Person(null, "Palmerg", null);
        persons = Arrays.asList(p1, p2);
        pR.saveAll(persons);
    }


    @Order(1)
    @Test
    public void testAddPerson() throws Exception {
        Person p1 = new Person(null, "Bauman", null);
        String json = ow.writeValueAsString(p1);
        mockMvc.perform(post("/api/property-service/persons").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isOk());
    }

    @Order(2)
    @Test
    public void testGetPersonByUsername() throws Exception {
        // get recently added user
        mockMvc.perform(get("/api/property-service/persons/username").param("username", "Bauman")).andExpect(matchAll(status().isOk(),
                jsonPath("$.username", hasToString("Bauman"))));
    }

    @Order(3)
    @Test
    public void testFailGetPersonByUsername() throws Exception {
        this.mockMvc.perform(get("/api/property-service/persons/username").param("username", "Jypth")).andExpect(status().isBadRequest());
    }

    @Order(4)
    @Test
    public void testUpdatePerson() throws Exception {
        persons.get(0).setUsername("update");
        String json = ow.writeValueAsString(persons.get(0));
        this.mockMvc.perform(put("/api/property-service/persons").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isOk()).andDo(print());
    }

    @Order(5)
    @Test
    public void testGetAllPersons() throws Exception {
        //since we added one person there should be 3 persons in db
        this.mockMvc.perform(get("/api/property-service/persons/all")).andExpect(matchAll(status().isOk(),
                jsonPath("$.*", hasSize(3)))).andDo(print());
    }

    @Order(6)
    @Test
    public void testPersonNameValidation() throws Exception {
        Person p1 = new Person(null, "Very long name, long name, long name ,long name, long name", null);
        String json = ow.writeValueAsString(p1);
        mockMvc.perform(post("/api/property-service/persons").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isBadRequest());
    }

    @Order(7)
    @Test
    public void testFailUpdateUnexistingPersonById() throws Exception {
        Person p = new Person(UUID.randomUUID(), "unexisting person", null);
        String json = ow.writeValueAsString(p);
        this.mockMvc.perform(put("/api/property-service/persons").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isBadRequest()).andDo(print());
    }

    @Order(8)
    @Test
    public void testDeletePerson() throws Exception {
        // deleting comment and checking if comment was deleted (there should be only 2 comments in db)
        this.mockMvc.perform(delete("/api/property-service/persons").param("id", persons.get(0).getId().toString()))
                .andExpect(status().isOk()).andDo(mvcResult -> mockMvc.perform(get("/api/property-service/persons/all"))
                .andExpect(matchAll(status().isOk(), jsonPath("$.*", hasSize(2)))));
    }

    @Order(9)
    @Test
    public void testFailDeleteRemovePerson() throws Exception {
        this.mockMvc.perform(delete("/api/property-service/persons").param("username",persons.get(0).getId().toString())).andExpect(status().isBadRequest());
    }

    @Order(10)
    @Test
    public void testFailGetUnexistingPerson() throws Exception {
        this.mockMvc.perform(get("/api/property-service/persons").param("id", UUID.randomUUID().toString()))
                .andExpect(status().isBadRequest()).andDo(print());
    }

    @Order(11)
    @Test
    public void  testPersonNotNullValidation() throws Exception {
        // testing username non-null property
        Person p1 = new Person(null, null, null);
        String json = ow.writeValueAsString(p1);
        mockMvc.perform(post("/api/property-service/persons").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().is4xxClientError());
    }

    @Order(12)
    @Test
    public void testAddSamePerson() throws Exception {
        Person p1 = new Person(null, "Bauman", null);
        String json = ow.writeValueAsString(p1);
        mockMvc.perform(post("/api/property-service/persons").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().is4xxClientError());
    }

    @AfterAll
    public void clearDatabase(@Autowired PersonRepository pR) {
        pR.deleteAll();
    }
*/
}
