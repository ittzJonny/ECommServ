package com.example.product_catalog__1.Users.TablePerClass;

import jakarta.persistence.Entity;

@Entity(name = "tpc_Instructor")
public class Instructor extends User {
    String company;
}
