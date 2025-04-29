package com.example.ecarthub.Controller;

import com.example.ecarthub.Client.ProductCategoryEComClient;
import com.example.ecarthub.DTO.AddToCartRequestDTO;
import com.example.ecarthub.DTO.AddToCartResponseDTO;
import com.example.ecarthub.DTO.ClientDTO.AuthUserGetUserDTO;
import com.example.ecarthub.DTO.ClientDTO.ProductsDTO;
import com.example.ecarthub.DTO.GetCartRequestDTO;
import com.example.ecarthub.DTO.GetCartResponseDTO;
import com.example.ecarthub.DTO.ServiceDTO.CartServiceResponseDTO;
import com.example.ecarthub.Model.Cart;
import com.example.ecarthub.Service.CartServiceInterface;
import com.example.ecarthub.Utils.MapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartServiceInterface cartService;

    @Autowired
    private ProductCategoryEComClient eComClient;

    @Autowired
    public CartController(CartServiceInterface cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public ResponseEntity<GetCartResponseDTO> getCart(@RequestParam("id") String userId, @RequestParam("type") String cartType) {

        CartServiceResponseDTO cartServiceResp = cartService.viewCart(userId, cartType);
        return new ResponseEntity<>(MapperUtils.getCartResponseDTO(cartServiceResp), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AddToCartResponseDTO> addToCart(@RequestBody AddToCartRequestDTO requestDTO) {
        System.out.println(requestDTO);
        CartServiceResponseDTO cartServiceResp=cartService.addToCart(requestDTO.getUserId(),requestDTO.getCartType(),requestDTO.getProductId(),requestDTO.getQuantity());
        AddToCartResponseDTO responseDTO=new AddToCartResponseDTO();
        responseDTO.setMessage("Item Successfully added to cart");
        responseDTO.setCart(MapperUtils.getCartResponseDTO(cartServiceResp));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PatchMapping
    public String updateItemQuantity() {
        return "Cart updated, default Return";
    }

    @DeleteMapping
    public String removeFromCart() {
        return "Item removed from cart, default Return";
    }

    @GetMapping("/checkout")
    public String checkout() {
        return "Checkout successful, default Return";
    }

    @PostMapping("/clearCart")
    public String clearCart() {
        return "Cart cleared, default Return";
    }



}
