package com.example.product_catalog__1.Controller;

import com.example.product_catalog__1.DTO.CategoryDTO;
import com.example.product_catalog__1.DTO.ProductDTO;
import com.example.product_catalog__1.DTO.ProductResponseDTO;
import com.example.product_catalog__1.DTO.SearchQueryRequestDTO;
import com.example.product_catalog__1.Models.Products;
import com.example.product_catalog__1.Services.SearchJPAService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;

@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private SearchJPAService searchService;

    @PostMapping("/product")
    public ResponseEntity<ProductResponseDTO> searchProduct(@RequestBody SearchQueryRequestDTO requestDTO) {
        try {
            System.out.println("------------------------------------"+requestDTO.getPageSize());
            Page<Products> productsList=searchService.searchProducts(
                    requestDTO.getSearchQuery(),
                    requestDTO.getPageNumber(),
                    requestDTO.getPageSize(),
                    requestDTO.getSortParams());
            return new ResponseEntity<>(convert(productsList), HttpStatus.OK);

        }
        catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(convert(e),HttpStatus.BAD_REQUEST);

        }
    }


    private ProductResponseDTO convert(Page<Products> products)
    {
        ProductResponseDTO responseDTO=new ProductResponseDTO();
        Page<ProductDTO> productDTOPage=products.map(  this::convertToResponseDTO);

        responseDTO.setProducts(productDTOPage);
        responseDTO.setMessage("Success");

        return responseDTO;

    }

    private ProductDTO convertToResponseDTO(Products products) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(products.getId());
        productDTO.setName(products.getName());
        productDTO.setPrice(products.getPrice());
        productDTO.setDescription(products.getDescription());
        productDTO.setImageUrl(products.getImageUrl());

        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(products.getCategory().getId());
        categoryDTO.setName(products.getCategory().getName());
        categoryDTO.setDescription(products.getCategory().getDescription());

        productDTO.setCategoryDTO(categoryDTO);

        return productDTO;
    }
    private ProductResponseDTO convert(Exception e) {
        ProductResponseDTO productResponseDTO = new ProductResponseDTO();
        productResponseDTO.setMessage(e.getMessage());
        return productResponseDTO;
    }

}
