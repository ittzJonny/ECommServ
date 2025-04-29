package com.example.ecarthub.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Entity
public class Cart extends BaseModel{
    String userId;

    @Enumerated(EnumType.STRING)
    CartType cartType;

    @OneToMany(mappedBy = "cart")
    List<CartItem> cartItemList;

}
