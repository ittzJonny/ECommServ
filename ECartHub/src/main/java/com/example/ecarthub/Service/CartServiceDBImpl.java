package com.example.ecarthub.Service;

import com.example.ecarthub.Client.AuthUserRestTemplateClient;
import com.example.ecarthub.Client.ProductCategoryEComClient;
import com.example.ecarthub.DTO.ClientDTO.AuthUserGetUserDTO;
import com.example.ecarthub.DTO.ClientDTO.ProductsDTO;
import com.example.ecarthub.DTO.ServiceDTO.CartItemServiceResponseDTO;
import com.example.ecarthub.DTO.ServiceDTO.CartServiceResponseDTO;
import com.example.ecarthub.Exceptions.DoesNotExistException;
import com.example.ecarthub.Exceptions.InvalidTypeException;
import com.example.ecarthub.Exceptions.MandatoryFieldException;
import com.example.ecarthub.Model.Cart;
import com.example.ecarthub.Model.CartItem;
import com.example.ecarthub.Model.CartType;
import com.example.ecarthub.Repo.CartItemRepo;
import com.example.ecarthub.Repo.CartRepo;
import com.example.ecarthub.Utils.MapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartServiceDBImpl implements CartServiceInterface{

    @Autowired
    private AuthUserRestTemplateClient authUserClient;

    @Autowired
    private ProductCategoryEComClient eComClient;

    @Autowired
    private CartRepo cartRepo;

    @Autowired
    private CartItemRepo cartItemRepo;


    @Override
    public CartServiceResponseDTO viewCart(String userId, String cartType) {

        this.getCartValidations(userId, cartType);
        AuthUserGetUserDTO user=authUserClient.getUserById(userId);

        Cart cart= cartRepo
                .findByUserIdAndCartType(userId, CartType.valueOf(cartType.toUpperCase()))
                .orElseThrow(()-> new DoesNotExistException("No Cart found with requested id: " + userId));

        List<CartItemServiceResponseDTO> cartItemServiceList= cart.getCartItemList()
                .stream()
                .map(cartItem -> {
                    ProductsDTO productsDTO=eComClient.findProductById(cartItem.getProductId());
                    return MapperUtils.getCartItemServiceResponseDTO(productsDTO, cartItem);
                })
                .toList();

        CartServiceResponseDTO cartServiceResponseDTO=new CartServiceResponseDTO();
        cartServiceResponseDTO.setCartType(CartType.valueOf(cartType.toUpperCase()));
        cartServiceResponseDTO.setCartItemList(cartItemServiceList);
        cartServiceResponseDTO.setUserEmail(user.getEmail());

        cartServiceResponseDTO.setTotalPrice(
                cartItemServiceList
                        .stream()
                        .mapToDouble(cartItem-> cartItem.getTotalItemPrice())
                        .sum()
        );


        return cartServiceResponseDTO;
    }

    @Override
    public CartServiceResponseDTO addToCart(String userId, String cartType, String productId, int quantity) {

        this.getCartValidations(userId, cartType);
        Optional<Cart> cartOptional= cartRepo
                .findByUserIdAndCartType(userId, CartType.valueOf(cartType.toUpperCase()));

        Cart cart= null;
        if (cartOptional.isEmpty())
        {
            cart=new Cart();
            cart.setCartType(CartType.valueOf(cartType.toUpperCase()));
            cart.setCartItemList(new ArrayList<>());
            cart.setUserId(userId);


        }
        else
        {
            cart=cartOptional.get();

        }

        CartItem cartItem=new CartItem();
        cartItem.setProductId(productId);
        cartItem.setQuantity(quantity);
        cartItem.setCart(cart);
        cart.getCartItemList().add(cartItem);

        cartRepo.save(cart);

        cartItemRepo.save(cartItem);


        return this.viewCart(userId, cartType);
    }

    @Override
    public Cart updateCart(String userId, CartType cartType, String productId, int quantity) {
        return null;
    }

    @Override
    public Cart removeFromCart(String userId, CartType cartType, String productId) {
        return null;
    }

    @Override
    public Cart checkout(String userId, CartType cartType) {
        return null;
    }

    @Override
    public Cart clearCart(String userId, CartType cartType) {
        return null;
    }


    //check for validity of UserId and cartID, they should be not null and correct
    private void getCartValidations(String userId, String cartType) {
        System.out.println("User id: " + userId+" "+"Cart type: "+cartType);
        if (cartType==null || cartType.isEmpty())
        {
            throw new MandatoryFieldException("Cart type cannot be null or empty");
        }
        if (userId==null || userId.isEmpty())
        {
            throw new MandatoryFieldException("User id cannot be null or empty");
        }

        try {
            CartType.valueOf(cartType.toUpperCase());
        }
        catch (IllegalArgumentException e) {
            throw new InvalidTypeException("Invalid cart type: " + cartType);
        }
    }

}
