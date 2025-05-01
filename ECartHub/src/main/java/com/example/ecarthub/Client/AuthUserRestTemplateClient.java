package com.example.ecarthub.Client;

import com.example.ecarthub.Config.ServicesBaseURLConfig;
import com.example.ecarthub.DTO.ClientDTO.AuthUserGetUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthUserRestTemplateClient implements AuthUserClientAdapter{

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ServicesBaseURLConfig servicesBaseURLConfig;

    public AuthUserGetUserDTO getUserById(String id) {
        String url = servicesBaseURLConfig.getAuthService() + id;
        AuthUserGetUserDTO user = restTemplate.getForObject(url, AuthUserGetUserDTO.class);
        System.out.println(user);
        return user;
    }
}
