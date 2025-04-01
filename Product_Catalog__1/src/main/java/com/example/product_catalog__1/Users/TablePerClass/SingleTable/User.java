package com.example.product_catalog__1.Users.TablePerClass.SingleTable;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "uset_type_disc", discriminatorType = DiscriminatorType.INTEGER)
public class User {
    @Id
    UUID Id;

    String email;
}
