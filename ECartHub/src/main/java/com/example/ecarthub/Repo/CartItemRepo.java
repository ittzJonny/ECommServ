package com.example.ecarthub.Repo;

import com.example.ecarthub.Model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CartItemRepo extends JpaRepository<CartItem, UUID> {
    CartItem save(CartItem cartItem);
}
