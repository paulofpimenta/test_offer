package com.api.demo.exception;


import org.springframework.validation.ObjectError;

import javax.validation.ConstraintViolationException;
import java.util.List;

public class UserApiException extends RuntimeException{

    public UserApiException(String message) {
        super(message);
    }

}
