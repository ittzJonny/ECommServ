package com.example.paymentservice.Service;

import com.example.paymentservice.Exceptions.payLinkException;
import com.razorpay.RazorpayException;

public interface PaymentServiceInterface {

    public String getPaymentLink(Long amount, String orderId, String phoneNumber, String name, String email) throws RazorpayException, payLinkException;
}
