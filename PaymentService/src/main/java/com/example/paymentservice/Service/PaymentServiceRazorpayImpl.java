package com.example.paymentservice.Service;

import com.example.paymentservice.Exceptions.payLinkException;
import com.example.paymentservice.PaymentGateways.PaymentGatewayChooserStrategy;
import com.example.paymentservice.PaymentGateways.PaymentGatewayInterface;
import com.razorpay.RazorpayException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceRazorpayImpl implements PaymentServiceInterface{


    @Autowired
    private PaymentGatewayChooserStrategy paymentGatewayChooserStrategy;

    public String getPaymentLink(Long amount, String orderId, String phoneNumber, String name, String email) throws  payLinkException {
        PaymentGatewayInterface paymentGateway=paymentGatewayChooserStrategy.getBestPaymentGateway();
        String link=paymentGateway.createStandardPaymentLink(amount,orderId,phoneNumber,name,email);

        return link;
    }

}
