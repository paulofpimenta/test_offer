package com.api.demo.exception;


import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

public class UserApiException extends ConstraintViolationException {

    public UserApiException(String message, Set<? extends ConstraintViolation<?>> constraintViolations) {
        super(message, constraintViolations);
    }

    public static class UserIsNotFrench extends Throwable {
        UserIsNotFrench(long id) {
            super("User residence is not France " + id);
        }
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
