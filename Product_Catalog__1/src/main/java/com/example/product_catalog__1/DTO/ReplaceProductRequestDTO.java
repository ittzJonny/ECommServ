package com.example.product_catalog__1.DTO;

import lombok.Data;

@Data
public class ReplaceProductRequestDTO {
    private Long id;
    private String title;
    private String description;
    private Double price;
    private String imageUrl;
    private String categoryName;
}
