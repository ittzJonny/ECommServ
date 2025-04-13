//package com.example.product_catalog__1.Services;
//
//import com.example.product_catalog__1.Client.FakeStoreClient;
//import com.example.product_catalog__1.DTO.FakeStoreProductDTO;
//import com.example.product_catalog__1.Models.Category;
//import com.example.product_catalog__1.Models.Products;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.web.client.RestTemplateBuilder;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.HttpStatusCode;
//import org.springframework.http.ResponseEntity;
//import org.springframework.lang.Nullable;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RequestCallback;
//import org.springframework.web.client.ResponseExtractor;
//import org.springframework.web.client.RestClientException;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.*;
//
//import static java.util.Objects.nonNull;
//
//@Service("fkps")
//public class FakeStoreProductService extends ProductServiceInterface{
//
//    @Autowired
//    private FakeStoreClient fakeStoreClient;
//
//    @Override
//    public Products updateProduct(Long productId, Products product) {
//        return convert(fakeStoreClient.updateProduct(productId,convert(product)));
//    }
//
//    @Override
//    public Products addProduct(Products product) {
//
//        return convert(fakeStoreClient.addProduct(convert(product)));
//
//    }
//
//    public List<Products> getAllProducts(){
//            List<Products> productsList=fakeStoreClient.getAllProducts().parallelStream()
//                    .map(fkDto-> convert(fkDto))
//                    .toList();
//
//            return productsList;
//    }
//
//    public Products getProductById(Long productId) {
////        restTemplateBuilder.rootUri("https://fakestoreapi.com/");
////        RestTemplate restTemplate = restTemplateBuilder.build();
////
////        String url="{products/productId}";
//
//        return convert(fakeStoreClient.getProductById(productId));
//
//
//    }
//
//
//    //sample fakestore
//    @Override
//    public Products getProductByUserRole(Long productId, UUID userId) {
//        return null;
//    }
//
//
//}
