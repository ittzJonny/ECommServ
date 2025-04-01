package com.example.bifrost;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class BifrostApplication {

    public static void main(String[] args) {
        SpringApplication.run(BifrostApplication.class, args);
    }

}
