package com.api.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.sql.Timestamp;
import java.util.List;

@Data
public class ErrorDetails {
    private int status;
    private String message;
    private List<String> errors;

    @JsonProperty("time")
    private Timestamp timeError;

    public ErrorDetails(int status, String message, List<String> errors, Timestamp time) {
        super();
        this.status = status;
        this.message = message;
        this.errors = errors;
        this.timeError = time;
    }
}

