package com.example.ecarthub.DTO;

import lombok.Data;

@Data
public class AddToCartRequestDTO {
    private String productId;
    private String userId;
    private String cartType;
    private Integer quantity;
}
