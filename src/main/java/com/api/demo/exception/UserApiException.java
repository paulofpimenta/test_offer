package com.api.demo.exception;


public class UserApiException extends RuntimeException{

    public UserApiException(String message) {
        super(message);
    }
}
