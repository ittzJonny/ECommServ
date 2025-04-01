package com.example.paymentservice.DTO;

import lombok.Data;

@Data
public class InitiatePaymentResponseDTO extends BaseResponseDTO{

    String paymentUrl;

}
