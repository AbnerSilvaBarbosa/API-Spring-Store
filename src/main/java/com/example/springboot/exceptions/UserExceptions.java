package com.example.springboot.exceptions;

import jakarta.persistence.EntityExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice()
public class UserExceptions {
    @ExceptionHandler(EntityExistsException.class)
    public ResponseEntity threatEmail409(){
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already exists");
    }
}
