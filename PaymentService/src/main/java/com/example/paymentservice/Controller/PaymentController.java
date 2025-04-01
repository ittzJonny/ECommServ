package com.example.paymentservice.Controller;

import com.example.paymentservice.DTO.InitiatePaymentRequestDTO;
import com.example.paymentservice.DTO.InitiatePaymentResponseDTO;
import com.example.paymentservice.Service.PaymentServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    PaymentServiceInterface paymentService;

    @PostMapping("/initiatePayment")
    public ResponseEntity<InitiatePaymentResponseDTO> initiatePayment(@RequestBody InitiatePaymentRequestDTO requestDTO) {
        try{
            String link = paymentService.getPaymentLink(
                    requestDTO.getAmount(),requestDTO.getOrderId(),
                    requestDTO.getPhoneNumber(), requestDTO.getName(), requestDTO.getEmail());

            return new ResponseEntity<>(createResponse(link), HttpStatus.OK);

        }
        catch(Exception e){

            return new ResponseEntity<>(createResponse(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private InitiatePaymentResponseDTO createResponse(String link)
    {
        InitiatePaymentResponseDTO responseDTO = new InitiatePaymentResponseDTO();
        responseDTO.setPaymentUrl(link);
        responseDTO.setMessage("Success");

        return responseDTO;
    }

    private InitiatePaymentResponseDTO createResponse(Exception e)
    {
        InitiatePaymentResponseDTO responseDTO = new InitiatePaymentResponseDTO();
        responseDTO.setMessage(e.getMessage());

        return responseDTO;
    }
}
