package com.example.authenticationservice.Controllers;

import com.example.authenticationservice.DTOs.UserDto;
import com.example.authenticationservice.Exceptions.DoesNotExistException;
import com.example.authenticationservice.Models.User;
import com.example.authenticationservice.Services.IUserService;
import com.example.authenticationservice.Utils.DtoUtil;
import com.example.authenticationservice.Utils.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    IUserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable UUID id) {
        try {
            User user= userService.getUserById(id);
            return new ResponseEntity<>(DtoUtil.convert(user), HttpStatus.OK);
        } catch (DoesNotExistException e) {
            return new ResponseEntity<>(ExceptionUtil.getExceptionResponse(e,UserDto.class), HttpStatus.NOT_FOUND);
        }
    }







}
