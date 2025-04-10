package com.example.product_catalog__1.Services;

import com.example.product_catalog__1.DTO.UserDto;
import com.example.product_catalog__1.Exceptions.DoesNotExistException;
import com.example.product_catalog__1.Exceptions.InvalidIdException;
import com.example.product_catalog__1.Models.Products;
import com.example.product_catalog__1.Repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

@Service("pss")
@Primary
public class ProductStorageService extends ProductServiceInterface{

    @Autowired
    ProductRepo productRepo;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public Products updateProduct(Long productId, Products product) {
        return null;
    }

    @Override
    public Products addProduct(Products product) {

        return productRepo.save(product);
    }

    @Override
    public List<Products> getAllProducts() throws DoesNotExistException{
        List<Products> pList=productRepo.findAll();
        if (pList.isEmpty()) throw new DoesNotExistException("No products found");

        return pList;
    }

    @Override
    public Products getProductById(Long productId) throws InvalidIdException {

        return productRepo
                .findProductsById(productId)
                .orElseThrow(()-> new InvalidIdException("No Product found with requested id: " + productId));
    }

    @Override
    public Products getProductByUserRole(Long productId, UUID userId) throws DoesNotExistException {
        Products p=productRepo.findProductsById(productId).orElseThrow(()-> new DoesNotExistException("Product Does not Exist"));

        UserDto userDTO=restTemplate.getForEntity("http://AuthenticationService/user/{id}", UserDto.class,userId).getBody();

        if (userDTO!=null)
        {
            return p;
        }

        return null;
    }
}
