package com.example.product_catalog__1.Controller;

import com.example.product_catalog__1.DTO.ProductDTO;
import com.example.product_catalog__1.Exceptions.InvalidIdException;
import com.example.product_catalog__1.Models.Products;
import com.example.product_catalog__1.Services.ProductServiceInterface;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@SpringBootTest
class ProductControllerTest {

    @Autowired
    private ProductController productController;


//    @MockBean
    @MockitoBean
    private ProductServiceInterface productService;

    @Captor
    private ArgumentCaptor<Long> idCaptor;


    @Test
    public void Test_getProductById_ReturnsProductSuccessfully_WithIdCapture() throws InvalidIdException {
        Long productId = 1L;
        Products products = new Products();
        products.setId(productId);
        products.setName("Iphone");
        products.setDescription("Description");

        when(productService.getProductById(productId)).thenReturn(products);



        //Act
        ResponseEntity<ProductDTO> result= productController.getProductById(productId);

        //Assert
        verify(productService).getProductById(idCaptor.capture()); //verify is used when you need to check on mocked bean
        assertEquals(productId, idCaptor.getValue());
    }


    @Test
    public void Test_getProductById_ReturnsProductSuccessfully() throws InvalidIdException {
        //Arrange

        Long productId = 1L;
        Products products = new Products();
        products.setId(productId);
        products.setName("Iphone");
        products.setDescription("Description");

        when(productService.getProductById(productId)).thenReturn(products);



        //Act
        ResponseEntity<ProductDTO> result= productController.getProductById(productId);

        //Asset
        assertNotNull(result);
        assertNotNull(result.getBody());
        assertEquals(productId, result.getBody().getId());
        assertEquals("Iphone", result.getBody().getName());
    }


    @Test
    public void Test_getProductById_CalledWithInvalID_ReturnsException() throws InvalidIdException {
        //Arrange
        Long productId = 0L;

        //Act and Assert
        Exception e=assertThrows(InvalidIdException.class, () -> productController.getProductById(productId));

        assertEquals("Id should be greater than 0", e.getMessage());


    }





    }