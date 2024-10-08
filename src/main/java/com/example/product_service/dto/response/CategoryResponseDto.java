package com.example.product_service.dto.response;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class CategoryResponseDto {
    private String name;
    private String description;
    private CategoryResponseDto subcategories;
}
