package com.example.product_catalog__1.Users.TablePerClass.SingleTable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity(name = "st_ta")
@DiscriminatorValue(value = "20")
public class Ta extends User {
    int helpRequests;
}
