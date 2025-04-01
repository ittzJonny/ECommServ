package com.example.product_catalog__1.Users.TablePerClass;

import jakarta.persistence.Entity;

@Entity(name = "tpc_mentor")
public class Mentor extends User {
    Double rating;
}
