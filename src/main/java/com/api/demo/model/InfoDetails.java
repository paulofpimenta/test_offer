package com.api.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;

public class InfoDetails {

    private int httpStatus;

    private String message;
    @JsonProperty("time")
    private Timestamp transactionTime;

    private Object result;


    public int getStatus() {
        return httpStatus;
    }

    public void setStatus(int httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Timestamp getTime() {
        return transactionTime;
    }

    public void setTime(Timestamp time) {
        this.transactionTime = time;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public InfoDetails(int httpStatus, String message, Timestamp time, Object results) {
        this.httpStatus = httpStatus;
        this.message = message;
        this.transactionTime = time;
        this.result = results;
    }


}
