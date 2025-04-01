package com.example.product_catalog__1.repos;

import com.example.product_catalog__1.Models.Category;
import com.example.product_catalog__1.Repos.CategoryRepo;
import com.example.product_catalog__1.Services.ProductServiceInterface;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

@SpringBootTest
public class CategoryRepoTest {

    @Autowired
    private CategoryRepo categoryRepo;


    @Test
    @Transactional
    void testFetchTypes()
    {
        Category category = categoryRepo.findById(122).orElse(null);
//        category.getProducts().forEach(p->System.out.println(p.getName()));
//        System.out.println(category);
    }

}
