package com.example.authenticationservice.Services;

import com.example.authenticationservice.Exceptions.DoesNotExistException;
import com.example.authenticationservice.Models.User;
import com.example.authenticationservice.Repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements IUserService{

    @Autowired
    UserRepo userRepo;

    @Override
    public User getUserById(UUID id) throws DoesNotExistException {
        return userRepo.findById(id).orElseThrow(()->new DoesNotExistException("No user with provided Id"));
    }
}
