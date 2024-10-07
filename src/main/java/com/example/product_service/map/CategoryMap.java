package com.example.product_service.map;

import com.example.product_service.dto.response.CategoryResponseDto;
import com.example.product_service.model.Category;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface CategoryMap {

    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "subcategories", target = "subcategories")
    CategoryResponseDto toCategoryDto(Category category);
    Category toEntity(CategoryResponseDto categoryDto);
    List<CategoryResponseDto> toDto(List<Category> categories);
}
