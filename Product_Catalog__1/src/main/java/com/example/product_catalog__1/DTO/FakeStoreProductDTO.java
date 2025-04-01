package com.example.product_catalog__1.DTO;


import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class FakeStoreProductDTO {
    private Long id;
    private String description;
    private Double price;
    private String title;
    private String category;
    private String image;

}
