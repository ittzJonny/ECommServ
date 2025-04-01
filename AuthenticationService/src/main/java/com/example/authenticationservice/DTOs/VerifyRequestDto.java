package com.example.authenticationservice.DTOs;


import com.example.authenticationservice.Models.Session;
import lombok.Data;

import java.util.UUID;

@Data
public class VerifyRequestDto {
    private String token;
    private UUID userId;
}
