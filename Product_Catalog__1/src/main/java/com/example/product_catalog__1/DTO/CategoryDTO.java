package com.example.product_catalog__1.DTO;

import com.example.product_catalog__1.Models.Products;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class CategoryDTO {

    private String name;
    private String description;

}