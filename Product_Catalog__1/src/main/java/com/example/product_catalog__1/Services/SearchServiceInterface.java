package com.example.product_catalog__1.Services;

import com.example.product_catalog__1.DTO.SortParams;
import com.example.product_catalog__1.Exceptions.DoesNotExistException;
import com.example.product_catalog__1.Models.Products;
import org.springframework.data.domain.Page;

import java.util.List;

public interface SearchServiceInterface {

    Page<Products> searchProducts(String searchQuery, int pageNumber, int pageSize, List<SortParams> sortParams) throws DoesNotExistException;
}
