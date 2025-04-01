package com.example.product_catalog__1.Controller;

import com.example.product_catalog__1.Exceptions.InvalidIdException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//@RestControllerAdvice
public class ControllerExceptionAdviser {

//    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handelException(Exception error){
        return new ResponseEntity<>(error.getMessage(), HttpStatus.CONFLICT);
    }
}
