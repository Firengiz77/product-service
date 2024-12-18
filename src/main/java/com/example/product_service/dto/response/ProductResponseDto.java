package com.example.product_service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponseDto {

    private String name;
    private String description;
    private Double price;
    private Integer stock;
    private String image;
    private CategoryProductDto category;
}