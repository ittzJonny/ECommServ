package com.example.ecarthub.DTO.ServiceDTO;

import com.example.ecarthub.Model.CartItem;
import com.example.ecarthub.Model.CartType;
import lombok.Data;


import java.util.List;

@Data
public class CartServiceResponseDTO {

    String userEmail;

    CartType cartType;

    List<CartItemServiceResponseDTO> cartItemList;
    Double totalPrice;
}
