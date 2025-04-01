package com.example.product_catalog__1.Users.TablePerClass;

import jakarta.persistence.*;

import java.util.UUID;

@Entity(name = "tpc_user")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class User {
    @Id
    UUID Id;

    String email;
}
