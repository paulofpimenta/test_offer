package com.api.demo.controller;


import com.api.demo.model.InfoDetails;
import com.api.demo.model.UserApi;
import com.api.demo.services.UserService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@RequestMapping("/user")
@RestController
@ControllerAdvice
@Validated
public class UserController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @Operation(summary = "Adds a user in the API Demo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User was successfully created ",
                    content = { @Content(mediaType = "application/json",schema = @Schema(implementation = UserApi.class)) }),
            @ApiResponse(responseCode = "400", description = "User cannot be created",
                    content = @Content)
    })
    @PostMapping(value = "/add", consumes = { "application/json"},produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addUserApi(@RequestBody  UserApi userApi, BindingResult bindingResult)  {
        UserApi userAdded = userService.save(userApi);
        String message = "User created with id " + userAdded.getId();
        InfoDetails infoDetails = new InfoDetails(HttpStatus.CREATED.value(),message,
                                                Timestamp.from(Instant.now()),userAdded);
        return new ResponseEntity<InfoDetails>(infoDetails,HttpStatus.CREATED);
    }

    @Operation(summary = "Find a user by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200  ", description = "A user was found",
                    content = { @Content(mediaType = "application/json",schema = @Schema(implementation = UserApi.class)) }),
            @ApiResponse(responseCode = "404 ", description = "User could not be found with the provided id",
                    content = @Content)
    })
    @GetMapping(value = "/get/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUserById( @Parameter( name = "id",required = true, description = "The user's id")
                                              @PathVariable("id") String id) {
        Optional <UserApi> userFound = userService.getUserApiDetails(id);
        String message = userFound.isEmpty() ? "User not found" : "User found with ID " + "'" + id + "'";
        HttpStatus statusCode = userFound.isEmpty() ? HttpStatus.NOT_FOUND :  HttpStatus.OK;
        InfoDetails infoDetails = new InfoDetails(statusCode.value(),message,Timestamp.from(Instant.now()),userFound);
        return new ResponseEntity<InfoDetails>(infoDetails,statusCode);
    }
    @Operation(summary = "Show all users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "A user or more found",
                    content = { @Content(mediaType = "application/json",schema = @Schema(implementation = UserApi.class)) }),
            @ApiResponse(responseCode = "200", description = "No users found in the database",
                    content = @Content)
    })
    @GetMapping(value = "/show_all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> showAllUsers() {
        List<UserApi> usersFound = userService.showAll();
        String message = usersFound.size() + " user(s) found in the database";
        InfoDetails infoDetails = new InfoDetails(HttpStatus.OK.value(),message,Timestamp.from(Instant.now()),usersFound);
        return new ResponseEntity<InfoDetails>(infoDetails,HttpStatus.OK);
    }
    @Hidden
    @RequestMapping("/")
    public @ResponseBody String greeting() {
        return "Welcome to the user demo API";
    }
}
