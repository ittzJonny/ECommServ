package com.example.product_catalog__1.Repos;

import com.example.product_catalog__1.Models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

    Optional<Category> findByName(String name);
}
