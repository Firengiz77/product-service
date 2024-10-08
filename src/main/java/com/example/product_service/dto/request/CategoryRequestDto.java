package com.example.product_service.dto.request;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class CategoryRequestDto {

    private String name;
    private String description;
    private Long subcategories;

}
