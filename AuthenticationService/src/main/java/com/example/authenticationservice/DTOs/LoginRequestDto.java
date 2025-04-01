package com.example.authenticationservice.DTOs;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class LoginRequestDto {

    private String email;
    private String password;
}
