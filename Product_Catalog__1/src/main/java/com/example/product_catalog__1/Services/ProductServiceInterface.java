package com.example.product_catalog__1.Services;

import com.example.product_catalog__1.DTO.FakeStoreProductDTO;
import com.example.product_catalog__1.Exceptions.DoesNotExistException;
import com.example.product_catalog__1.Exceptions.InvalidIdException;
import com.example.product_catalog__1.Exceptions.MandatoryFieldException;
import com.example.product_catalog__1.Exceptions.ResourceAlreadyExists;
import com.example.product_catalog__1.Models.Category;
import com.example.product_catalog__1.Models.Products;

import java.util.List;
import java.util.UUID;

public abstract class  ProductServiceInterface {

    public abstract List<Products> getAllProducts() throws DoesNotExistException;
    public abstract Products getProductById(Long productId) throws InvalidIdException;
    public abstract Products getProductByUserRole(Long productId, UUID userId) throws DoesNotExistException;

    public abstract Products addProduct(Products product) throws ResourceAlreadyExists, MandatoryFieldException;

    public abstract Products replaceProduct(Products newProduct) throws DoesNotExistException, MandatoryFieldException;



    public abstract Products updatePartialProduct(String productName, Products productToUpdate) throws DoesNotExistException, MandatoryFieldException;




    public Products convert(FakeStoreProductDTO fakeStoreProductDTO)
    {

        if (fakeStoreProductDTO == null) return null;
        Products products = new Products();
        products.setId(fakeStoreProductDTO.getId());
        products.setName(fakeStoreProductDTO.getTitle());
        products.setPrice(fakeStoreProductDTO.getPrice());
        products.setDescription(fakeStoreProductDTO.getDescription());
        products.setImageUrl(fakeStoreProductDTO.getImage());

        Category category = new Category();
        category.setName(fakeStoreProductDTO.getCategory());

        products.setCategory(category);

        return products;
    }

    FakeStoreProductDTO convert(Products product)
    {
        if (product == null) return null;
        FakeStoreProductDTO fakeStoreProductDTO = new FakeStoreProductDTO();
        fakeStoreProductDTO.setImage(product.getImageUrl());
        fakeStoreProductDTO.setTitle(product.getName());
        fakeStoreProductDTO.setPrice(product.getPrice());
        fakeStoreProductDTO.setDescription(product.getDescription());
        fakeStoreProductDTO.setCategory(product.getCategory().getName());
        return fakeStoreProductDTO;
    }
}
