package com.example.product_catalog__1.DTO;

import com.example.product_catalog__1.Models.Category;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDTO {

    private String name;
    private String description;
    private String imageUrl;
    private Double price;
    private CategoryDTO categoryDTO;
}
