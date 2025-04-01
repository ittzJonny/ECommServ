package com.example.product_catalog__1.Users.TablePerClass.JoinClass;

import jakarta.persistence.*;

import java.util.UUID;

//@MappedSuperclass
@Entity(name = "jc_user")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    @Id
    UUID Id;

    String email;
}
