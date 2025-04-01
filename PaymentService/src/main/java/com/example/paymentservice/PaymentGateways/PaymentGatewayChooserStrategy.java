package com.example.paymentservice.PaymentGateways;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentGatewayChooserStrategy {

    @Autowired
    private RazorPayPaymentGatewayImpl razorPayPaymentGateway;

    @Autowired
    private StripePaymentGatewayImpl stripePaymentGateway;


    // will be chosen based on previous success frequency returning stripe gateway for now
    public PaymentGatewayInterface getBestPaymentGateway() {
        return stripePaymentGateway;
    }

    public PaymentGatewayInterface getRazorPayPaymentGateway() {
        return razorPayPaymentGateway;
    }

    public PaymentGatewayInterface getStripePaymentGateway() {
        return stripePaymentGateway;
    }

}
