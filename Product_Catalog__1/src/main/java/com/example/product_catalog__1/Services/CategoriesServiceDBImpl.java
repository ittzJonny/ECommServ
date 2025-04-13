package com.example.product_catalog__1.Services;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriesServiceDBImpl extends CategoriesServiceInterface{


    @Override
    public List<String> getAllCategories() {
        return List.of();
    }
}
