package com.lms.exception;


import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.naming.AuthenticationException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AuthenticationException.class)
    public String handleOtpVerificationException(AuthenticationException ex) {

        log.info("This Is My Custom Exception ");

        return "My Bad Message";
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<?> handleNoSuchElementException(NoSuchElementException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> handleDuplicateEmailException(DataIntegrityViolationException ex) {
        return new ResponseEntity<>("This Email Already In Our Database", HttpStatus.CONFLICT);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleInvalidMessages(MethodArgumentNotValidException ex) {
        Map<String, String> mapError = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            mapError.put(error.getObjectName(), error.getDefaultMessage());
            System.out.println(error.getObjectName() + " " + error.getDefaultMessage());
        });
        return mapError;
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @org.springframework.web.bind.annotation.ExceptionHandler(RuntimeException.class)
    public String handleRunTimeExceptionError(RuntimeException exception) {
        return exception.getMessage();
    }




}
