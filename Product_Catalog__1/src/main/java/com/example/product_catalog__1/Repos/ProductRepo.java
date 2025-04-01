package com.example.product_catalog__1.Repos;

import com.example.product_catalog__1.Models.Products;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<Products,Long> {

    Optional<Products> findProductsById(Long id);

    Page<Products> findProductsByNameLike(String name, Pageable pageable);

    List<Products> findAll();
    void save(Long id);

}
