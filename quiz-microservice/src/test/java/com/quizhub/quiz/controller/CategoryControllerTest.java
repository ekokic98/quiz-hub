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

        import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAllCategoriesTest() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .get("/api/quiz-service/categories/all")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void getCategoryTest() throws Exception {
        String id = (String) addCategory("Category 5").get("id");

        RequestBuilder request = MockMvcRequestBuilders
                .get("/api/quiz-service/categories")
                .param("id", id)
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andReturn();
    }

    private Map addCategory(String name) throws Exception {
        RequestBuilder postRequest = MockMvcRequestBuilders
                .post("/api/quiz-service/categories")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"name\": \"" + name + "\"\n" +
                        "    \"imageUrl\": null\n" +
                        "}");

        MvcResult mvcResult = mockMvc.perform(postRequest)
                .andReturn();

        String json = mvcResult.getResponse().getContentAsString();
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, Map.class);
    }

}
