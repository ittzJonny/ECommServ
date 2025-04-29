package com.example.ecarthub.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
public class CartItem extends BaseModel{

    private String productId;
    private Integer quantity;

    @ManyToOne
    private Cart cart;

}
