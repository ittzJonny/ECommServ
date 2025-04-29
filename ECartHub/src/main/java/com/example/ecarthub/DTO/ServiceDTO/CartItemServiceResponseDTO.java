package com.example.ecarthub.DTO.ServiceDTO;

import lombok.Data;

@Data
public class CartItemServiceResponseDTO {

    private String productName;
    private Integer quantity;
    private String imageUrl;
    private Double totalItemPrice;
}
