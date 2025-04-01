package com.example.paymentservice.PaymentGateways;

import com.example.paymentservice.Exceptions.payLinkException;
import com.razorpay.PaymentLink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.json.JSONObject;
import com.razorpay.Payment;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;


@Component
public class RazorPayPaymentGatewayImpl implements PaymentGatewayInterface{


    // RazorPay doc https://razorpay.com/docs/api/payments/payment-links/create-standard/


    @Autowired
    private RazorpayClient razorpayClient;



    public String createStandardPaymentLink(Long amount, String orderId, String phoneNumber, String name, String email) throws payLinkException {

        PaymentLink razorPayPaymentLink=this.configureRazorPayRequet(amount, orderId, phoneNumber, name, email);


        return razorPayPaymentLink.get("short_url").toString(); // get the key value from razorPay documentation from sample response on Internet "https://razorpay.com/docs/api/payments/payment-links/create-standard/"

    }

    private PaymentLink configureRazorPayRequet(Long amount, String orderId, String phoneNumber, String name, String email) throws payLinkException
    {
        JSONObject paymentLinkRequest = new JSONObject();
        paymentLinkRequest.put("amount",amount);
        paymentLinkRequest.put("currency","INR");
        paymentLinkRequest.put("accept_partial",true);
        paymentLinkRequest.put("first_min_partial_amount",100);
        paymentLinkRequest.put("expire_by",this.getExpireyTimestamp());
        paymentLinkRequest.put("reference_id",orderId);
        paymentLinkRequest.put("description","Payment for policy no #23456");

        JSONObject customer = new JSONObject();
        customer.put("name",phoneNumber);
        customer.put("contact",name);
        customer.put("email",email);
        paymentLinkRequest.put("customer",customer);

        JSONObject notify = new JSONObject();
        notify.put("sms",true);
        notify.put("email",true);
        paymentLinkRequest.put("notify",notify);

        paymentLinkRequest.put("reminder_enable",true);

        JSONObject notes = new JSONObject();
        notes.put("policy_name","Jeevan Bima");
        paymentLinkRequest.put("notes",notes);
        paymentLinkRequest.put("callback_url","https://example-callback-url.com/");
        paymentLinkRequest.put("callback_method","get");

        PaymentLink payment ;
        try {
             payment = razorpayClient.paymentLink.create(paymentLinkRequest);
        }
        catch (Exception e) {
            throw new payLinkException("RazorPay: "+e.getMessage());
        }


        return payment;
    }

    private long getExpireyTimestamp(){
        long currentEpochMillis = Instant.now().toEpochMilli(); // Get current epoch in milliseconds

        long futureEpochMillis = Instant.ofEpochMilli(currentEpochMillis)
                .plus(20, ChronoUnit.MINUTES)
                .toEpochMilli();

        return futureEpochMillis;
    }

}
