package com.example.product_catalog__1.Models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
public class Products extends BaseModel {

    private String name;
    private String description;
    private String imageUrl;
    private Double price;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Category category;

    @Override
    public String toString() {
        return "Products{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", price=" + price +
                ", category=" + category.getName()+"("+category.getId()+") " +
                '}';
    }
}
