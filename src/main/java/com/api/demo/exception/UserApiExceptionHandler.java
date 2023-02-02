package com.api.demo.exception;

import com.api.demo.model.ErrorDetails;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class UserApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value
            = { ConstraintViolationException.class,UserApiException.class })
    protected ResponseEntity<Object> handleConflict(ConstraintViolationException ex, WebRequest request) {

        List<String> errors = ex.getConstraintViolations().stream()
                              .map(e-> e.getMessage()).collect((Collectors.toList()));

        ErrorDetails errorDetails = new ErrorDetails(HttpStatus.UNPROCESSABLE_ENTITY.value(),
                                    "User API could not be saved",errors,Timestamp.from(Instant.now()));
        return handleExceptionInternal(ex, errorDetails,
                new HttpHeaders(), HttpStatus.UNPROCESSABLE_ENTITY, request);
    }
}



