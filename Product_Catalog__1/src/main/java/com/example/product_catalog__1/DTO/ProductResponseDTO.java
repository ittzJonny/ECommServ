package com.example.product_catalog__1.DTO;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
public class ProductResponseDTO extends BaseResponseDTO{

    Page<ProductDTO> products;



}
