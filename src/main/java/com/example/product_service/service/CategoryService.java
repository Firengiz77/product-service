package com.example.product_service.service;

import com.example.product_service.dto.request.CategoryRequestDto;
import com.example.product_service.dto.response.CategoryResponseDto;
import com.example.product_service.exception.CategoryNotFoundException;
import com.example.product_service.map.CategoryMap;
import com.example.product_service.model.Category;
import com.example.product_service.repository.CategoryRepository;
import com.example.product_service.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMap categoryMap;
    private final JwtTokenUtil jwtTokenUtil;

    public List<CategoryResponseDto> getCategories() {
        return categoryMap.toDto(categoryRepository.findAll());
    }

    public CategoryResponseDto getCategory(Long id) {
        return categoryMap.toCategoryDto(categoryRepository.findById(id).orElseThrow(()->new CategoryNotFoundException()));
    }

    public CategoryResponseDto createCategory(CategoryRequestDto categoryDto,String token) {
        String userId = jwtTokenUtil.extractUserId(token);
        Category category = Category.builder()
                .name(categoryDto.getName())
                .description(categoryDto.getDescription())
                .subcategories(categoryDto.getSubcategories() == 0 ? null : categoryRepository.findById(categoryDto.getSubcategories()).orElseThrow(()->new CategoryNotFoundException()) )
                .userId(userId)
                .build();
        return categoryMap.toCategoryDto(categoryRepository.save(category));
    }

    public CategoryResponseDto updateCategory(Long id, CategoryRequestDto categoryDto) {
        Category category = categoryRepository.findById(id).orElseThrow(()->new CategoryNotFoundException());
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        category.setSubcategories(categoryDto.getSubcategories() == 0 ? null : categoryRepository.findById(categoryDto.getSubcategories()).orElseThrow(()->new CategoryNotFoundException()));
        return categoryMap.toCategoryDto(categoryRepository.save(category));
    }

    public String deleteCategory(Long id) {
        categoryRepository.deleteById(id);
        return "Category deleted";
    }

}
