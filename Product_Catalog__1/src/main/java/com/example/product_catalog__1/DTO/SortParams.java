package com.example.product_catalog__1.DTO;

import lombok.Data;

@Data
public class SortParams {

    private SortType sortType=SortType.ASC;
    private String sortCriteria;
}
