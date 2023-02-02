package com.api.demo.exception;


import org.springframework.validation.ObjectError;

import javax.validation.ConstraintViolationException;
import java.util.List;

public class UserApiException extends RuntimeException{

    public UserApiException(List<ObjectError> message,Throwable err) {
    }


    public static class UserIsUnder18 extends Throwable {
        UserIsUnder18(long birthDate) {
            super("User is under 18 with birth date " + birthDate);
        }
    }

    public static class UserNotFound extends Throwable {
        UserNotFound(long id) {
            super("Could not find user " + id);
        }
    }

    public static class UserPhoneNotValid extends Throwable {
        UserPhoneNotValid(String message) {
            super(message);
        }
    }

}
