package com.example.paymentservice.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Webhook")
public class WebhookController {

    @PostMapping
    public void webhook(@RequestBody String event) {
        System.out.println(event);
    }
}
