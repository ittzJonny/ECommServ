package com.example.product_catalog__1.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jdk.jfr.Enabled;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;

@Getter
@Setter
@ToString
@Entity
public class Category extends BaseModel{
    private String name;
    private String description;

    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
//    @Fetch(FetchMode.JOIN)
    private List<Products> products;

}
