package com.example.ecarthub.Repo;

import com.example.ecarthub.Model.Cart;
import com.example.ecarthub.Model.CartType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CartRepo extends JpaRepository<Cart, UUID> {

    Optional<Cart> findByUserIdAndCartType(String userId, CartType cartType);

}
