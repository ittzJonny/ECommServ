package com.example.authenticationservice.DTOs;

import lombok.Data;

@Data
public abstract class BaseResponseDTO {

    public String responseMessage;
    public BaseResponseStatus responseStatus;

}
