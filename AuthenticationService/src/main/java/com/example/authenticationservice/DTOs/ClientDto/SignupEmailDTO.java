package com.example.authenticationservice.DTOs.ClientDto;

import lombok.Data;

@Data
public class SignupEmailDTO {
    String fromEmail;
    String toEmail;
    String subject;
    String body;


}
