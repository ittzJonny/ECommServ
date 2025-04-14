package com.example.authenticationservice.DTOs;

import lombok.Data;

@Data
public class FaultResponseDTO extends BaseResponseDTO {

    public FaultResponseDTO(){}
    public FaultResponseDTO(String responseMessage) {
        super.responseMessage = responseMessage;
    }

}
