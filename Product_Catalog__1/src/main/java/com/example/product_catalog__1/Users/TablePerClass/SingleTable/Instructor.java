package com.example.product_catalog__1.Users.TablePerClass.SingleTable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity(name = "st_instuctor")
@DiscriminatorValue(value = "30")
public class Instructor extends User {
    String company;
}
