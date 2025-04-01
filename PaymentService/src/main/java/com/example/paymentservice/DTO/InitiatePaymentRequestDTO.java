package com.example.paymentservice.DTO;

import lombok.Data;

@Data
public class InitiatePaymentRequestDTO {

    private Long amount;
    private String orderId ;
    private String phoneNumber;
    private String name;
    private String email;
}
