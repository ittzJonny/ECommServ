package com.example.product_catalog__1.Controller;

import com.example.product_catalog__1.Services.CategoriesServiceInterface;
//import com.example.product_catalog__1.Services.FakeStoreCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {


    @Autowired
    CategoriesServiceInterface categoriesService;

    @GetMapping("/")
    public List<String> getAllCategories() {
        return categoriesService.getAllCategories();
    }

    @RequestMapping(path = "cat", method = RequestMethod.GET)
    public String getCategory() {
        return "hey this is cat";
    }

}

