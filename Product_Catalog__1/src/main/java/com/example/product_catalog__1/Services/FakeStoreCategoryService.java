package com.example.product_catalog__1.Services;

import com.example.product_catalog__1.Client.FakeStoreCategoryClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class FakeStoreCategoryService extends CategoriesServiceInterface{

    @Autowired
    FakeStoreCategoryClient fakeStoreCategoryClient;

    @Override
    public List<String> getAllCategories() {
        return Arrays.asList(fakeStoreCategoryClient.getAllCategories());
    }
}
