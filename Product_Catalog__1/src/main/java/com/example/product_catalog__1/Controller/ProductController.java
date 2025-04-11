package com.example.product_catalog__1.Controller;

import com.example.product_catalog__1.DTO.*;
import com.example.product_catalog__1.Exceptions.DoesNotExistException;
import com.example.product_catalog__1.Exceptions.InvalidIdException;
import com.example.product_catalog__1.Exceptions.MandatoryFieldException;
import com.example.product_catalog__1.Exceptions.ResourceAlreadyExists;
import com.example.product_catalog__1.Models.Products;
import com.example.product_catalog__1.Services.ProductServiceInterface;
import com.example.product_catalog__1.Util.MapperUtils;
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

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getProducts() throws DoesNotExistException{

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();

        List<ProductDTO> ans = new ArrayList<>();
        List<Products> productsList = productService.getAllProducts();

        for (Products product : productsList) {
            ans.add(convertProductToDTO(product));
        }

//        if (ans.isEmpty()) throw new DoesNotExistException("No products found");
        headers.add("Error Status", "Success");
        return new ResponseEntity<>(ans, headers, HttpStatus.OK);
    }

    @GetMapping("/getProductByRole/{productId}/{userId}")
    public ResponseEntity<ProductDTO> getProductByRole(@PathVariable Long productId, @PathVariable UUID userId) throws DoesNotExistException {
        Products product=productService.getProductByUserRole(productId, userId);
        return new ResponseEntity<>(convertProductToDTO(product), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) throws InvalidIdException {
        if (id <= 0) {
            throw new InvalidIdException("Id should be greater than 0");
        }
        Products products = productService.getProductById(id);
        if (products == null) {
            throw new InvalidIdException("Product id " + id + " not found");
        }


        return new ResponseEntity<>(convertProductToDTO(products), HttpStatus.OK);

    }

    private ProductDTO convertProductToDTO(Products products) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setDescription(products.getDescription());
        productDTO.setName(products.getName());
        productDTO.setPrice(products.getPrice());
        productDTO.setImageUrl(products.getImageUrl());


        if (productDTO.getCategoryDTO() != null) {


            CategoryDTO categoryDTO = new CategoryDTO();
            categoryDTO.setName(products.getCategory().getName());

            productDTO.setCategoryDTO(categoryDTO);
        }

        return productDTO;
    }

    @PostMapping
    public ResponseEntity<ProductDTO> addProduct(@RequestBody Products products) throws ResourceAlreadyExists, MandatoryFieldException {

        Products p = productService.addProduct(products);

        if (p == null) throw new RuntimeException("Product creation failed");
        return new ResponseEntity<>(convertProductToDTO(p), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ProductDTO> updateProduct(@RequestBody ReplaceProductRequestDTO requestDTO) throws DoesNotExistException, MandatoryFieldException {

        Products p= productService.replaceProduct(MapperUtils.mapReplaceProductRequestDTOtoProducts(requestDTO));
        return new ResponseEntity<>(convertProductToDTO(p),HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<PatchProcuctResponseDTO> updatePartialProduct(@RequestBody CreateProductDTO createProductDTO) throws DoesNotExistException, MandatoryFieldException {
        System.out.println(createProductDTO.getCategoryName()+"       "+createProductDTO.getCategoryDescription());

        Products products=productService.updatePartialProduct(createProductDTO.getTitle(), MapperUtils.mapCreateProductDTOtoProducts(createProductDTO));

        ProductDTO productDTO=MapperUtils.convertProductToDTO(products);
        PatchProcuctResponseDTO responseDTO=new PatchProcuctResponseDTO();
        responseDTO.setProductDTO(productDTO);

        return new ResponseEntity<>(responseDTO,HttpStatus.OK);
    }


}
