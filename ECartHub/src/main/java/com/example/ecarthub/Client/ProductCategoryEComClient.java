package com.example.ecarthub.Client;

import com.example.ecarthub.Config.ServicesBaseURLConfig;
import com.example.ecarthub.DTO.ClientDTO.ProductsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ProductCategoryEComClient implements ProductCategoryClientAdapter {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ServicesBaseURLConfig servicesBaseURLConfig;

    public ProductsDTO findProductById(String productId) {

        String url = servicesBaseURLConfig.getProductService() + productId;
        return restTemplate.getForEntity(url, ProductsDTO.class).getBody();
    }

}
