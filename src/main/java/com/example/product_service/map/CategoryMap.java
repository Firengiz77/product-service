package com.example.product_service.map;

import com.example.product_service.dto.CategoryDto;
import com.example.product_service.model.Category;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface CategoryMap {

    CategoryDto toCategoryDto(Category category);
    Category toEntity(CategoryDto categoryDto);
    List<CategoryDto> toDto(List<Category> categories);

}
