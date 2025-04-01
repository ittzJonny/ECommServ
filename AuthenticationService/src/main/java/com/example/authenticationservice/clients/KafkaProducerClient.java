package com.example.authenticationservice.clients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducerClient {

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    public void sendEventToQueue(String topic, String event)
    {
        kafkaTemplate.send(topic, event);
    }
}
