package com.example.authenticationservice.DTOs;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class SignupRequestDto {

    private String email;
    private String password;

}
