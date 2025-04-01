package com.example.product_catalog__1.Client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class FakeStoreCategoryClient {

    @Autowired
    RestTemplateBuilder restTemplateBuilder;

    private <T> T validations(ResponseEntity<T> responseEntity)
    {
        if (responseEntity.getStatusCode() != HttpStatus.OK || responseEntity.getBody() == null)
        {
            return null;
        }

        return responseEntity.getBody();
    }

    public String[] getAllCategories()
    {
        RestTemplate restTemplate= restTemplateBuilder.build();
        ResponseEntity<String[]> re=restTemplate.getForEntity("https://fakestoreapi.com/products/categories",String[].class);
        return validations(re);

    }

}
