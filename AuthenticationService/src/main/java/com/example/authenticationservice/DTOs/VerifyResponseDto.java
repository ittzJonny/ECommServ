package com.example.authenticationservice.DTOs;

import lombok.Data;

import java.util.UUID;

@Data
public class VerifyResponseDto extends BaseResponseDTO{
    private String token;
    private UUID userId;
    private boolean tokenValidity;
}
