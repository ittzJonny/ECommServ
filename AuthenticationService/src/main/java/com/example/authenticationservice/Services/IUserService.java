package com.example.authenticationservice.Services;

import com.example.authenticationservice.Exceptions.DoesNotExistException;
import com.example.authenticationservice.Models.User;

import java.util.UUID;

public interface IUserService  {
    public User getUserById(UUID id) throws DoesNotExistException;
}
