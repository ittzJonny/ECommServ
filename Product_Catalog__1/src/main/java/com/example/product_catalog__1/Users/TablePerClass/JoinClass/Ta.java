package com.example.product_catalog__1.Users.TablePerClass.JoinClass;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity(name = "jc_ta")
@PrimaryKeyJoinColumn(name = "user_id")
public class Ta extends User {
    int helpRequests;
}
