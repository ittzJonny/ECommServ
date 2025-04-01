package com.example.product_catalog__1.Users.TablePerClass;

import jakarta.persistence.Entity;

@Entity(name = "tpc_ta")
public class Ta extends User {
    int helpRequests;
}
