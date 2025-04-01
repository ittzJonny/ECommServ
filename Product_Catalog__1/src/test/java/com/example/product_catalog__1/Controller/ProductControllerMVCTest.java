package com.example.product_catalog__1.Controller;

import com.example.product_catalog__1.DTO.ProductDTO;
import com.example.product_catalog__1.Models.Category;
import com.example.product_catalog__1.Models.Products;
import com.example.product_catalog__1.Services.ProductServiceInterface;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = ProductController.class)
public class ProductControllerMVCTest {

    @MockitoBean
    @Qualifier("pss")
    private ProductServiceInterface productService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;


    @Test
    public void Test_GetProducts_ReturnsProductsSuccessfully() throws Exception {
        //Arrage
        Products p1=new Products();
        p1.setId(1L);
        p1.setName("Iphone");

        Products p2=new Products();
        p2.setId(2L);
        p2.setName("Mac");

        List<Products> Productlist=new ArrayList<>();
        Productlist.add(p1);
        Productlist.add(p2);

        List<ProductDTO> list=new ArrayList<>();
        ProductDTO pd1=new ProductDTO();
        pd1.setId(1L);
        pd1.setName("Iphone");

        ProductDTO pd2=new ProductDTO();
        pd2.setId(2L);
        pd2.setName("Mac");


        list.add(pd1);
        list.add(pd2);

        when(productService.getAllProducts()).thenReturn(Productlist);

        //Act
       mockMvc.perform(get("/products/")).andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(list)));
//                 expect(body().inStringFormat(object mapper will convert json into string ))



    }


    @Test
    public void Test_CreateProduct_ReturnsSavedProductSuccessfully() throws Exception {
        //Arrange
        Products p1=new Products();
        p1.setId(1L);
        p1.setName("Iphone");
        p1.setPrice(23.0);
        p1.setCategory(new Category());

        when(productService.addProduct(any(Products.class))).thenReturn(p1);

        ProductDTO pd1=new ProductDTO();
        pd1.setId(1L);
        pd1.setName("Iphone");
        pd1.setPrice(23.0);

        //Act and assert
        mockMvc.perform(post("/products")
                        .content(objectMapper.writeValueAsString(p1))
                        .contentType(MediaType.APPLICATION_JSON)
                        )
                        .andExpect(status().isCreated())
                        .andExpect(content().string(objectMapper.writeValueAsString(pd1)))
                        .andExpect(jsonPath("$.id").value(pd1.getId()))
                        .andExpect(jsonPath("$.name").value(pd1.getName()));



    }
}
