package com.example.product_service.dto.response;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryResponseDto {
    private String name;
    private String description;
    private CategoryResponseDto subcategories;
}
