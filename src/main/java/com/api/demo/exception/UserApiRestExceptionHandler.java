package com.api.demo.exception;

import com.api.demo.model.ErrorDetails;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class UserApiRestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ ConstraintViolationException.class })
    public ResponseEntity<ErrorDetails> handleConstraintViolation(final ConstraintViolationException ex) {
        //logger.info(ex.getClass().getName());
        final List<String> errors = ex.getConstraintViolations().stream().map(error->error.getMessage()).collect(Collectors.toList());
        final ErrorDetails apiError = new ErrorDetails(HttpStatus.BAD_REQUEST.value(),"User constraints violated", errors, Timestamp.from(Instant.now()));
        return new ResponseEntity<ErrorDetails>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler({ UserApiException.class })
    public ResponseEntity<ErrorDetails> handleUserNotFound(final UserApiException ex) {
        //logger.info(ex.getClass().getName());
        final List<String> errors = ex.getMessage().lines().toList();
        final ErrorDetails apiError = new ErrorDetails(HttpStatus.NOT_FOUND.value(),"User not found", errors, Timestamp.from(Instant.now()));
        return new ResponseEntity<ErrorDetails>(apiError, new HttpHeaders(), apiError.getStatus());
    }
}
