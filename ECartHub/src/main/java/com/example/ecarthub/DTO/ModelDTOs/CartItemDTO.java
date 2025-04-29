package com.example.ecarthub.DTO.ModelDTOs;

import com.example.ecarthub.Model.Cart;
import jakarta.persistence.ManyToOne;
import lombok.Data;


@Data
public class CartItemDTO {

    private String productName;
    private String productImage;
    private Integer quantity;
    private Double totalItemPrice;
}
