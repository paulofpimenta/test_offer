package com.api.demo.controller;

import com.api.demo.model.UserApi;
import com.api.demo.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserAPIControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @Autowired
    private UserService userService;

    @Test
    public void testMockMVC() throws Exception {

        mockMvc.perform(get("/user/"))
                .andExpect(status().isOk());
    }
    @Test
    public void whenUserIsAddedThenThenResponseReturnsCreateAndUserName() throws Exception {
        UserApi user = new UserApi("Jonny","12/10/1980","FR","0450304050","M");
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String userAsJson = ow.writeValueAsString(user);
        mockMvc.perform(post("/user/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userAsJson))
                        .andExpect(status().isCreated())
                        .andExpect(jsonPath("$.result.name", is(user.getName())));
    }

    @Test
    public void whenUserUnder18IsAddedThenResponseReturnsBadRequest() throws Exception {
        UserApi user = new UserApi("Robert","12/10/2015","FR","0625252359","M");
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String userAsJson = ow.writeValueAsString(user);
        mockMvc.perform(post("/user/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userAsJson))
                        .andExpect(status().isBadRequest());
    }
    @Test
    public void whenUserLivesOutOfFranceIsAddedReturnsBadRequest() throws Exception {
        UserApi user = new UserApi("Ane","03/07/2004","UK","0625464020","F");
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String userAsJson = ow.writeValueAsString(user);
        mockMvc.perform(post("/user/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userAsJson))
                .andExpect(status().isBadRequest());
    }
    @Test
    public void userWithInvalidFrenchNumberIsAddedReturnsBadRequest() throws Exception {
        UserApi user = new UserApi("Peter","12/10/1990","FR","22232310-23323","M");
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String userAsJson = ow.writeValueAsString(user);
        mockMvc.perform(post("/user/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userAsJson))
                        .andExpect(status().isBadRequest());
    }
    @Test
    public void userWith4ViolatedConstraintsIsAddedReturns4Errors() throws Exception {
        UserApi user = new UserApi("Peter","12/10/2020","UK","22232310-23323","FR");
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String userAsJson = ow.writeValueAsString(user);
        mockMvc.perform(post("/user/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userAsJson))
                        .andExpect(status().isBadRequest())
                        .andExpect(jsonPath("$.errors", hasSize(4)));
    }

}

