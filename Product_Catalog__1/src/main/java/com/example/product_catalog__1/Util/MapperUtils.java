package com.example.product_catalog__1.Util;

import com.example.product_catalog__1.DTO.CategoryDTO;
import com.example.product_catalog__1.DTO.CreateProductDTO;
import com.example.product_catalog__1.DTO.ProductDTO;
import com.example.product_catalog__1.DTO.ReplaceProductRequestDTO;
import com.example.product_catalog__1.Models.Category;
import com.example.product_catalog__1.Models.Products;
import org.springframework.stereotype.Component;

@Component
public class MapperUtils {

    public static Products mapReplaceProductRequestDTOtoProducts(ReplaceProductRequestDTO dto) {
        Products products=new Products();
        products.setId(dto.getId());
        products.setName(dto.getTitle());
        products.setDescription(dto.getDescription());
        products.setImageUrl(dto.getImageUrl());
        products.setPrice(dto.getPrice());


        Category category=new Category();
        category.setName(dto.getCategoryName());

        products.setCategory(category);
        return products;
    }

    public static Products mapCreateProductDTOtoProducts(CreateProductDTO createProductDTO) {

        Products products=new Products();
        products.setName(createProductDTO.getTitle());
        products.setDescription(createProductDTO.getDescription());
        products.setImageUrl(createProductDTO.getImageUrl());
        products.setPrice(createProductDTO.getPrice());

        Category category=new Category();
        category.setName(createProductDTO.getCategoryName());
        category.setDescription(createProductDTO.getDescription());

        products.setCategory(category);
        return products;
    }


    public static ProductDTO convertProductToDTO(Products products) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setDescription(products.getDescription());
        productDTO.setName(products.getName());
        productDTO.setPrice(products.getPrice());
        productDTO.setImageUrl(products.getImageUrl());


        if (products.getCategory() != null) {


            CategoryDTO categoryDTO = new CategoryDTO();
            categoryDTO.setName(products.getCategory().getName());
            categoryDTO.setDescription(products.getCategory().getDescription());

            productDTO.setCategoryDTO(categoryDTO);
        }

        return productDTO;
    }

}
