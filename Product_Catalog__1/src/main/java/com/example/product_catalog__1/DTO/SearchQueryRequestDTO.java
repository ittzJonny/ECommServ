package com.example.product_catalog__1.DTO;

import lombok.Data;

import java.util.List;

@Data
public class SearchQueryRequestDTO {

    private int pageSize;
    private int pageNumber;
    private String searchQuery;
    List<SortParams> sortParams;
}
