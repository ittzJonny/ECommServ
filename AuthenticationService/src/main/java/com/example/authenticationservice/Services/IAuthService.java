package com.example.authenticationservice.Services;

import com.example.authenticationservice.Exceptions.AlreadyExistsException;
import com.example.authenticationservice.Exceptions.DoesNotExistException;
import com.example.authenticationservice.Exceptions.MisMatchException;
import com.example.authenticationservice.Models.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.data.util.Pair;

import java.util.UUID;

public abstract class IAuthService {

    public abstract User signUp(String username, String password) throws AlreadyExistsException, JsonProcessingException;
    public abstract Pair<User,String> login(String username, String password) throws MisMatchException, DoesNotExistException;
    public abstract boolean validateToken(String token, UUID userId) throws DoesNotExistException;

}
