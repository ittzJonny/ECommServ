package com.example.product_catalog__1.Repos;

import com.example.product_catalog__1.Models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Integer> {
}
