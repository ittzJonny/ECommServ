package com.example.ecarthub.DTO;

import com.example.ecarthub.DTO.ModelDTOs.CartItemDTO;
import lombok.Data;

import java.util.List;

@Data
public class GetCartResponseDTO {
    String userEmail;
    List<CartItemDTO> cartItems;
    Double totalPrice;
}
