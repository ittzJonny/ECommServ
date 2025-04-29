package com.example.ecarthub.DTO;

import lombok.Data;

@Data
public class AddToCartResponseDTO {

    private GetCartResponseDTO cart;
    private String message;
}
