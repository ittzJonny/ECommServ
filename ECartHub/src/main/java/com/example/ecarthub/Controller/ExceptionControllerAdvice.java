package com.example.ecarthub.Controller;

import com.example.ecarthub.Exceptions.DoesNotExistException;
import com.example.ecarthub.Exceptions.InvalidTypeException;
import com.example.ecarthub.Exceptions.MandatoryFieldException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(DoesNotExistException.class)
    public ResponseEntity<String> handleDoesNotExistException(DoesNotExistException ex) {
        return ResponseEntity.status(HttpStatus.OK).body(ex.getMessage());
    }

    @ExceptionHandler(InvalidTypeException.class)
    public ResponseEntity<String> handleInvalidRelationIdException(InvalidTypeException ex) {
        return ResponseEntity.status(HttpStatus.OK).body(ex.getMessage());
    }

    @ExceptionHandler(MandatoryFieldException.class)
    public ResponseEntity<String> handleMandatoryFieldException(MandatoryFieldException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }



}
