package com.example.ecarthub.DTO.ClientDTO;


import lombok.Data;

@Data
public class ProductsDTO {

    private String name;
    private String description;
    private String imageUrl;
    private Double price;

    private CategoryDTO category;
}
