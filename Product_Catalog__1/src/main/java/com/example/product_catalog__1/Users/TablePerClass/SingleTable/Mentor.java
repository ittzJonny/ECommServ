package com.example.product_catalog__1.Users.TablePerClass.SingleTable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity(name = "st_mentor")
@DiscriminatorValue(value = "10")
public class Mentor extends User {
    Double rating;
}
