package com.example.paymentservice.PaymentGateways;

import com.example.paymentservice.Exceptions.payLinkException;
import com.razorpay.RazorpayException;

public interface PaymentGatewayInterface {

    public String createStandardPaymentLink(Long amount, String orderId, String phoneNumber, String name, String email) throws payLinkException;
}
