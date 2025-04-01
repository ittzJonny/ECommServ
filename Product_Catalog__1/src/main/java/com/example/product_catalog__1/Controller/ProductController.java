package com.example.product_catalog__1.Controller;

import com.example.product_catalog__1.DTO.CategoryDTO;
import com.example.product_catalog__1.DTO.ProductDTO;
import com.example.product_catalog__1.Exceptions.InvalidIdException;
import com.example.product_catalog__1.Models.Products;
import com.example.product_catalog__1.Services.ProductServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController{

    @Autowired
    @Qualifier("pss")
    private ProductServiceInterface productService;

    @GetMapping("/")
    public ResponseEntity<List<ProductDTO>> getProducts(){

        try {
            MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();

            List<ProductDTO> ans = new ArrayList<>();
            List<Products> productsList = productService.getAllProducts();

            for (Products product : productsList) {
                ans.add(convertProductToDTO(product));
            }

            if (ans.isEmpty()) throw new RuntimeException("No products found");

            headers.add("Error Status", "Success");
            return new ResponseEntity<>(ans, headers, HttpStatus.OK);
        }
        catch (Exception e) {
            throw e;
        }
    }

    @GetMapping("/getProductByRole/{productId}/{userId}")
    public ResponseEntity<ProductDTO> getProductByRole(@PathVariable Long productId, @PathVariable UUID userId){
        try {
            Products product=productService.getProductByUserRole(productId, userId);
            return new ResponseEntity<>(convertProductToDTO(product), HttpStatus.OK);
        }
        catch (Exception e) {
            throw e;
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) throws InvalidIdException {
        try {
            if (id <= 0) {
                throw new InvalidIdException("Id should be greater than 0");
            }
            Products products = productService.getProductById(id);
            if (products == null) {
                throw new InvalidIdException("Product id " + id + " not found");
            }


            return new ResponseEntity<>(convertProductToDTO(products), HttpStatus.OK);
        }
        catch (InvalidIdException e) {
            throw e;
        }

    }

    private ProductDTO convertProductToDTO(Products products) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setDescription(products.getDescription());
        productDTO.setName(products.getName());
        productDTO.setPrice(products.getPrice());
        productDTO.setImageUrl(products.getImageUrl());
        productDTO.setId(products.getId());

        if (productDTO.getCategoryDTO() != null) {


            CategoryDTO categoryDTO = new CategoryDTO();
            categoryDTO.setName(products.getCategory().getName());
            categoryDTO.setId(products.getCategory().getId());

            productDTO.setCategoryDTO(categoryDTO);
        }

        return productDTO;
    }

    @PostMapping
    public ResponseEntity<ProductDTO> addProduct(@RequestBody Products products){

        try {
            System.out.println("------------------------- "+
                    products+" "+products.getId()
            );
            Products p = productService.addProduct(products);

            if (p == null) throw new RuntimeException("Product creation failed");
            return new ResponseEntity<>(convertProductToDTO(p), HttpStatus.CREATED);
        }
        catch (Exception e) {
            throw e;
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id,@RequestBody Products products){
        Products p= productService.updateProduct(id,products);
        return new ResponseEntity<>(convertProductToDTO(p),HttpStatus.OK);
    }
}
//
//
//@Test
//public void Test_CreateProduct_RunsSuccessfully() throws Exception {
//    //Arrange
//    Product product = new Product();
//    product.setId(5L);
//    product.setName("Ipad");
//    when(productService.save(any(Product.class))).thenReturn(product);
//    ProductDto productDto = new ProductDto();
//    productDto.setId(5L);
//    productDto.setName("Ipad");
//    //Act and Assert
//    mockMvc.perform(post("/products").content(
//                            objectMapper.writeValueAsString(productDto))
//                    .contentType(MediaType.APPLICATION_JSON))
//            .andExpect(status().isOk())
//            .andExpect(
//                    content().string(objectMapper.writeValueAsString(productDto)))
//            .andExpect(jsonPath("$.id").value(product.getId()))
//            .andExpect(jsonPath("$.name").value(product.getName()));
//}
