package com.example.product_catalog__1.Client;

import com.example.product_catalog__1.DTO.FakeStoreProductDTO;
import com.example.product_catalog__1.Models.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component
public class FakeStoreClient {

    @Autowired
    RestTemplateBuilder restTemplateBuilder;

    private <T> ResponseEntity<T> requestForEntity(String url, HttpMethod httpMethod, @Nullable Object request, Class<T> responseType, Object... uriVariables) throws RestClientException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        System.out.println("-------------------------------------------------------------"+request);
        return restTemplate.execute(url, httpMethod, requestCallback, responseExtractor, uriVariables);
    }


    private <T> T validations(ResponseEntity<T> responseEntity)
    {
        if(!responseEntity.getStatusCode().equals(HttpStatusCode.valueOf(200))&&responseEntity.getBody()==null)
        {
            return null;
        }

        return responseEntity.getBody();
    }

    public FakeStoreProductDTO getProductById(Long id) {

        String url = "https://fakestoreapi.com/products/{productId}";
        ResponseEntity<FakeStoreProductDTO> rE=this.requestForEntity(url, HttpMethod.GET, null, FakeStoreProductDTO.class, id);
        return  validations(rE);
    }

    public List<FakeStoreProductDTO> getAllProducts()
    {
        String url = "https://fakestoreapi.com/products";
        ResponseEntity<FakeStoreProductDTO[]> rE=this.requestForEntity(url, HttpMethod.GET, null, FakeStoreProductDTO[].class);
        FakeStoreProductDTO[] fDto=validations(rE);
        if (fDto==null)
        {
            return null;
        }
        return new ArrayList<>(Arrays.asList(rE.getBody()));
    }

    public FakeStoreProductDTO addProduct(FakeStoreProductDTO product)
    {
        String url = "https://fakestoreapi.com/products";
        ResponseEntity<FakeStoreProductDTO> responseEntity=
                this.requestForEntity(url, HttpMethod.POST, product, FakeStoreProductDTO.class);
        return  validations(responseEntity);
    }

    public FakeStoreProductDTO updateProduct(Long id,FakeStoreProductDTO product)
    {
        String url = "https://fakestoreapi.com/products/{id}";
        ResponseEntity<FakeStoreProductDTO> responseEntity=
                this.requestForEntity(url,HttpMethod.PUT,product,FakeStoreProductDTO.class,id);
        return  validations(responseEntity);
    }
}
