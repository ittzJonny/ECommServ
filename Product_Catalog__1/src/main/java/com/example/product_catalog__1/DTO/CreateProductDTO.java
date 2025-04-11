package com.example.product_catalog__1.DTO;

import com.example.product_catalog__1.Models.Category;
import com.example.product_catalog__1.Models.Products;
import lombok.Data;

@Data
public class CreateProductDTO {
//    private Long id;
    private String title;
    private String description;
    private Double price;
    private String imageUrl;
    private String categoryName;
    private String categoryDescription;

    public static CreateProductDTO fromProduct(Products product) {
        CreateProductDTO responseDto = new CreateProductDTO();
//        responseDto.setId(product.getId());
        responseDto.setDescription(product.getDescription());
        responseDto.setTitle(product.getName());
        responseDto.setPrice(product.getPrice());
        responseDto.setImageUrl(product.getImageUrl());
        responseDto.setCategoryName(product.getCategory().getName());
        responseDto.setCategoryDescription(product.getCategory().getDescription());
        return responseDto;
    }

    public Products toProduct() {
        Products product = new Products();
        product.setName(this.title);
        product.setDescription(this.description);
        product.setPrice(this.price);
        product.setImageUrl(this.imageUrl);
        Category category = new Category();
        category.setName(categoryName);
        category.setDescription(categoryDescription);
        product.setCategory(category);

        return product;
    }
}
