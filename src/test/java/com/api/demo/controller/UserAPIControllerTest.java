package com.api.demo.controller;

import com.api.demo.model.UserApi;
import com.api.demo.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

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

    /*
     * Jackson mapper for Object -> JSON conversion
     */
    @Autowired
    ObjectMapper mapper;

    @Autowired
    private UserService userService;

    List<UserApi> usersApiList;

    @Before
    @Test
    public void getAllUsersReturnsOkWithListOfUsers() throws Exception {

        usersApiList = new ArrayList<UserApi>();

        usersApiList.add(UserApi.builder().name("Peter").id(String.valueOf(new ObjectId()))
                        .gender("M").birthDate("12/10/1980").phoneNumber("0450304050")
                        .countryOfResidence("FR").build());
        usersApiList.add(UserApi.builder().name("Vitoria").id(String.valueOf(new ObjectId()))
                        .gender("F").birthDate("23/07/1991").phoneNumber("0589293961")
                        .countryOfResidence("FR").build());
        usersApiList.add(UserApi.builder().name("Alexander").id(String.valueOf(new ObjectId()))
                        .gender("R").birthDate("01/12/2000").phoneNumber("0634598238")
                        .countryOfResidence("FR").build());
        usersApiList.add(UserApi.builder().name("Mathieu").id(String.valueOf(new ObjectId()))
                        .gender("").birthDate("21/03/2002").phoneNumber("0787394957")
                        .countryOfResidence("FR").build());

        // Mocking out the vehicle service
        Mockito.when(userService.showAll()).thenReturn(usersApiList);

        mockMvc.perform(get("/user/show_all"))
                .andExpect(status().isFound())
                .andExpect(jsonPath("$.message", is("4 user(s) found in the database")));
    }

    @Test
    public void testMockMVC() throws Exception {

        mockMvc.perform(get("/user/"))
                .andExpect(status().isOk());
    }
    @Test
    public void userIsFoundReturnsIsFound() throws Exception {
        String userID = usersApiList.get(0).getId();
        mockMvc.perform(get("/user/get/" + userID))
                .andExpect(status().isFound())
                .andExpect(jsonPath("$.message", is(String.format("User found with ID '%s'",userID))));
    }

    @Test
    public void userIsAddedReturnsIsCreated() throws Exception {
        UserApi user = UserApi.builder().name("Joanna").id(String.valueOf(new ObjectId()))
                .gender("F").birthDate("12/10/1990").phoneNumber("0225234050")
                .countryOfResidence("FR").build();
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String userAsJson = ow.writeValueAsString(user);
        mockMvc.perform(post("/user/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userAsJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.result.id", is(user.getId())));
    }

    @Test
    public void userUnder18IsAddedReturnsUnProcessed() throws Exception {
        UserApi user = UserApi.builder().name("Robert").id(String.valueOf(new ObjectId()))
                .gender("M").birthDate("12/10/2015").phoneNumber("0625252359")
                .countryOfResidence("FR").build();
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String userAsJson = ow.writeValueAsString(user);
        mockMvc.perform(post("/user/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userAsJson))
                .andExpect(status().isCreated());
    }
    @Test
    public void userLivesOutOfFranceIsAddedReturnsUnProcessed() throws Exception {
        UserApi user = UserApi.builder().name("Ane").id(String.valueOf(new ObjectId()))
                .gender("F").birthDate("03/07/2004").phoneNumber("0625464020")
                .countryOfResidence("UK").build();
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String userAsJson = ow.writeValueAsString(user);
        mockMvc.perform(post("/user/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userAsJson))
                .andExpect(status().isUnprocessableEntity());
    }
    @Test
    public void userWithInvalidFrenchNumberReturnsUnProcessed() throws Exception {
        UserApi user = UserApi.builder().name("Jonny").id(String.valueOf(new ObjectId()))
                .gender("M").birthDate("12/10/1990").phoneNumber("22232310-23323")
                .countryOfResidence("FR").build();
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String userAsJson = ow.writeValueAsString(user);
        mockMvc.perform(post("/user/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userAsJson))
                .andExpect(status().isUnprocessableEntity());
    }

}

