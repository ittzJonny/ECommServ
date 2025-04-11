package com.example.product_catalog__1.Controller;

import com.example.product_catalog__1.Exceptions.DoesNotExistException;
import com.example.product_catalog__1.Exceptions.InvalidIdException;
import com.example.product_catalog__1.Exceptions.MandatoryFieldException;
import com.example.product_catalog__1.Exceptions.ResourceAlreadyExists;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionAdviser {

    @ExceptionHandler(DoesNotExistException.class)
    public ResponseEntity<String> handelException(Exception error){
        return new ResponseEntity<>(error.getMessage(), HttpStatus.OK);
    }


    @ExceptionHandler(InvalidIdException.class)
    public ResponseEntity<String> handelException(InvalidIdException error){
        {
            return new ResponseEntity<>(error.getMessage(), HttpStatus.OK);
        }
    }

    @ExceptionHandler(MandatoryFieldException.class)
    public ResponseEntity<String> handelException(MandatoryFieldException error){
        {
            return new ResponseEntity<>(error.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @ExceptionHandler(ResourceAlreadyExists.class)
    public ResponseEntity<String> handelException(ResourceAlreadyExists error){
        return new ResponseEntity<>(error.getMessage(), HttpStatus.CONFLICT);
    }
}
