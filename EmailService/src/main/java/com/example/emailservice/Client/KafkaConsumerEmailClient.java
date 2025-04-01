package com.example.emailservice.Client;

import com.example.emailservice.DTO.EmailDTO;
import com.example.emailservice.Util.EmailUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

@Component
public class KafkaConsumerEmailClient {
    @Autowired
    private ObjectMapper objectMapper;

    @KafkaListener(topics = "test-topic", groupId = "emailServicef")
    public void sendEmail(String event) throws JsonProcessingException {
        System.out.println("Heer=========================================================================");
        EmailDTO emailDTO = objectMapper.readValue(event, EmailDTO.class);

        System.out.println("TLSEmail Start=========================================================================");
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
        props.put("mail.smtp.port", "587"); //TLS Port
        props.put("mail.smtp.auth", "true"); //enable authentication
        props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS

        //create Authenticator object to pass in Session.getInstance argument
        Authenticator auth = new Authenticator() {
            //override the getPasswordAuthentication method
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emailDTO.getFromEmail(), "iamtoocool ");
            }
        };
        Session session = Session.getInstance(props, auth);

        EmailUtil.sendEmail(session, emailDTO.getToEmail(),emailDTO.getSubject(),emailDTO.getBody());

    }


}
