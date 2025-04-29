package com.example.authenticationservice.Controllers;

import com.example.authenticationservice.DTOs.BaseResponseDTO;
import com.example.authenticationservice.DTOs.FaultResponseDTO;
import com.example.authenticationservice.Exceptions.AlreadyExistsException;
import com.example.authenticationservice.Exceptions.DoesNotExistException;
import com.fasterxml.jackson.core.JsonProcessingException;
//import com.thoughtworks.xstream.converters.reflection.MissingFieldException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionControllerAdviser {

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<FaultResponseDTO> handleAlreadyExistsException(AlreadyExistsException exception) {
        return new ResponseEntity<>(new FaultResponseDTO(exception.getMessage()), HttpStatus.OK);
    }

    @ExceptionHandler(DoesNotExistException.class)
    public ResponseEntity<FaultResponseDTO> handleDoesNotExistException(DoesNotExistException exception) {
        return new ResponseEntity<>(new FaultResponseDTO(exception.getMessage()), HttpStatus.OK);
    }

//    @ExceptionHandler(MissingFieldException.class)
//    public ResponseEntity<FaultResponseDTO> handleMissingFieldException(MissingFieldException exception) {
//        return  new ResponseEntity<>(new FaultResponseDTO(exception.getMessage()), HttpStatus.OK);
//    }

    @ExceptionHandler(JsonProcessingException.class)
    public ResponseEntity<String> handleJsonProcessingException(JsonProcessingException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private <T> T userExceptionDto(Exception e, Class<T> tClass)
    {
        try{
            T t=tClass.getDeclaredConstructor().newInstance();
            if (t instanceof BaseResponseDTO) {
                ((BaseResponseDTO) t).setResponseMessage(e.getMessage()+" "+e.getClass());
            }
            return t;
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
            return null;
        }

    }
}
