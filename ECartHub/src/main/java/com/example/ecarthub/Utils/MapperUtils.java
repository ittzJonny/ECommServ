package com.example.ecarthub.Utils;

import com.example.ecarthub.DTO.ClientDTO.ProductsDTO;
import com.example.ecarthub.DTO.GetCartRequestDTO;
import com.example.ecarthub.DTO.GetCartResponseDTO;
import com.example.ecarthub.DTO.ModelDTOs.CartItemDTO;
import com.example.ecarthub.DTO.ServiceDTO.CartItemServiceResponseDTO;
import com.example.ecarthub.DTO.ServiceDTO.CartServiceResponseDTO;
import com.example.ecarthub.Model.Cart;
import com.example.ecarthub.Model.CartItem;

import java.util.ArrayList;

public class MapperUtils {

    public static CartItemServiceResponseDTO getCartItemServiceResponseDTO(ProductsDTO productsDTO, CartItem cartItem) {

        CartItemServiceResponseDTO responseDTO = new CartItemServiceResponseDTO();
        responseDTO.setProductName(productsDTO.getName());
        responseDTO.setImageUrl(productsDTO.getImageUrl());
        responseDTO.setQuantity(cartItem.getQuantity());
        responseDTO.setTotalItemPrice(cartItem.getQuantity() * productsDTO.getPrice());

        return responseDTO;
    }

    public static CartItemDTO getCartItemDTO(CartItemServiceResponseDTO incomingDTO)
    {
        CartItemDTO dto= new CartItemDTO();
        dto.setProductName(incomingDTO.getProductName());
        dto.setProductImage(incomingDTO.getImageUrl());
        dto.setQuantity(incomingDTO.getQuantity());
        dto.setTotalItemPrice(incomingDTO.getTotalItemPrice());

        return dto;
    }

    public static GetCartResponseDTO getCartResponseDTO(CartServiceResponseDTO incomingDTO) {
        GetCartResponseDTO responseDTO = new GetCartResponseDTO();

        responseDTO.setUserEmail(incomingDTO.getUserEmail());
        responseDTO.setTotalPrice(incomingDTO.getTotalPrice());
        responseDTO.setCartItems(
                incomingDTO.getCartItemList().stream().map(item-> getCartItemDTO(item)).toList()
        );

        return responseDTO;
    }
}
