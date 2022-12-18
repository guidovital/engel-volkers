package com.engelvolkers.test.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import lombok.extern.slf4j.Slf4j;

/**
 * Control Exception Handler class
 * 
 * @author Guilherme Vital
 *
 */
@Slf4j
@ControllerAdvice
public class ControlExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<GenericError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {
        log.error("Entity not found: {}", e.getMessage());
        HttpStatus status = HttpStatus.NOT_FOUND;
        var err = new GenericError(LocalDateTime.now(), status.value(), e.getMessage(), request.getRequestURI(), null,
                e.getMessage());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(EngelVolkersException.class)
    public ResponseEntity<GenericError> dtException(EngelVolkersException e, HttpServletRequest request) {
        log.error("Business exception: {}", e.getMessage());
        HttpStatus status = HttpStatus.ACCEPTED;
        var err = new GenericError(LocalDateTime.now(), status.value(), e.getMessage(), request.getRequestURI(), null,
                e.getMessage());
        return ResponseEntity.status(status).body(err);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GenericError> handleValidationExceptions(MethodArgumentNotValidException e,
            HttpServletRequest request) {
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        HttpStatus status = HttpStatus.BAD_REQUEST;
        var err = new GenericError(LocalDateTime.now(), status.value(), errors.toString(), request.getRequestURI(),
                null, e.getMessage());
        log.error("Method Argument Not Valid Exception: {}", err.getErrorMessage());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<GenericError> handleValidationExceptions(InvalidFormatException e,
            HttpServletRequest request) {
        log.error("Invalid Format Exception: {}", e.getMessage());
        HttpStatus status = HttpStatus.BAD_REQUEST;
        var err = new GenericError(LocalDateTime.now(), status.value(), e.getMessage(), request.getRequestURI(), null,
                e.getMessage());
        return ResponseEntity.status(status).body(err);
    }

}
