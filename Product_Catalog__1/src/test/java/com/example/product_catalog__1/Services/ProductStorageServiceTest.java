package com.example.product_catalog__1.Services;

import com.example.product_catalog__1.DTO.UserDto;
import com.example.product_catalog__1.Exceptions.DoesNotExist;
import com.example.product_catalog__1.Models.Products;
import com.example.product_catalog__1.Models.State;
import com.example.product_catalog__1.Repos.ProductRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
//import  org.mockito.Mockito.when;

import static org.mockito.Mockito.*;

@SpringBootTest
class ProductStorageServiceTest {

    @Autowired
    private ProductStorageService productStorageService;

    @MockitoBean
    private ProductRepo productRepo;

    @MockitoBean
    private RestTemplate restTemplate;

    @Test
    void convert() {
    }

    @Test
    void testConvert() {
    }

    @Test
    void updateProduct() {
    }

    @Test
    void addProduct() {
    }

    @Test
    void getAllProducts() {
    }

    @Test
    void getProductById() {
    }

    @Test
    void getProductByUserRole_ValidProduct_ValidUser_Good() {
        //Arrange;
        Long productId=1L;
        UUID userId=UUID.randomUUID();

        Products product = new Products();
        product.setId(productId);
        product.setName("test");
        product.setDescription("This is test product");
        product.setPrice(29.0);
        product.setState(State.Active);

        UserDto userDTO=new UserDto();


        when(productRepo.findProductsById(productId)).thenReturn(Optional.of(product));

        when(restTemplate.getForEntity("http://AuthenticationService/user/{id}", UserDto.class,userId))
                .thenReturn(new ResponseEntity<>(userDTO, HttpStatus.OK));


        //Act
        Products response=productStorageService.getProductByUserRole(productId,userId);



        //Assert
        assertNotNull(response);
        assertEquals(product.getId(), response.getId());
        assertEquals(product.getName(), response.getName());
        assertEquals(product.getDescription(), response.getDescription());
        assertEquals(product.getPrice(), response.getPrice());
        assertEquals(product.getState(), response.getState());
    }

    @Test
    void getProductByUserRole_NullProduct_ValidUser_ThrowsException_DoesNotExist() {
        //Arrange;
        Long productId=1L;
        UUID userId=UUID.randomUUID();

        Products product = null;

        UserDto userDTO=new UserDto();


        when(productRepo.findProductsById(productId)).thenReturn(Optional.empty());

//        when(restTemplate.getForEntity("http://AuthenticationService/user/{id}", UserDto.class,userId))
//                .thenReturn(new ResponseEntity<>(userDTO, HttpStatus.OK));



        //Assert
        Exception e=assertThrows(DoesNotExist.class,() -> productStorageService.getProductByUserRole(productId,userId));
        assertEquals("Product Does not Exist", e.getMessage());
    }

    @Test
    void getProductByUserRole_ValidProduct_NullUser_ReturnsNull() {
        //Arrange;
        Long productId=1L;
        UUID userId=UUID.randomUUID();

        Products product = new Products();
        product.setId(productId);
        product.setName("test");
        product.setDescription("This is test product");
        product.setPrice(29.0);
        product.setState(State.Active);

        UserDto userDTO=null;


        when(productRepo.findProductsById(productId)).thenReturn(Optional.of(product));

        when(restTemplate.getForEntity("http://AuthenticationService/user/{id}", UserDto.class,userId))
                .thenReturn(new ResponseEntity<>(userDTO, HttpStatus.OK));



        //Assert
        assertNull(productStorageService.getProductByUserRole(productId, userId));


    }

    @Test
    void getProductByUserRole_InvalidProduct_ValidUser_ReturnsNull() {
        //Arrange;
        Long productId=-1L;
        UUID userId=UUID.randomUUID();

        Optional<Products> productOptional = Optional.empty();


        when(productRepo.findProductsById(productId)).thenReturn(productOptional);

//        when(restTemplate.getForEntity("http://AuthenticationService/user/{id}", UserDto.class,userId))
//                .thenReturn(new ResponseEntity<>(userDTO, HttpStatus.OK));



        //Assert
        Exception e=assertThrows(DoesNotExist.class,() -> productStorageService.getProductByUserRole(productId,userId));
        assertEquals("Product Does not Exist", e.getMessage());


    }
}