package com.example.ecarthub.Service;

import com.example.ecarthub.DTO.ClientDTO.AuthUserGetUserDTO;
import com.example.ecarthub.DTO.ServiceDTO.CartServiceResponseDTO;
import com.example.ecarthub.Model.Cart;
import com.example.ecarthub.Model.CartType;

public interface CartServiceInterface {

    CartServiceResponseDTO viewCart(String userId, String cartType) ;
    CartServiceResponseDTO addToCart(String userId, String cartType, String productId, int quantity) ;
    Cart updateCart(String userId,CartType cartType, String productId, int quantity) ;
    Cart removeFromCart(String userId, CartType cartType, String productId) ;
    Cart checkout(String userId, CartType cartType) ;
    Cart clearCart(String userId, CartType cartType) ;


}
