package com.example.product_service.controller;


import com.example.product_service.dto.request.CategoryRequestDto;
import com.example.product_service.dto.response.CategoryResponseDto;
import com.example.product_service.exception.NoAccessException;
import com.example.product_service.exception.NoAuthenticationException;
import com.example.product_service.service.CategoryService;
import com.example.product_service.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private final JwtTokenUtil jwtTokenUtil;

    @GetMapping
    public List<CategoryResponseDto> getCategories() {
        return categoryService.getCategories();
    }

    @GetMapping("/{id}")
    public CategoryResponseDto getCategory(@PathVariable Long id) {
        return categoryService.getCategory(id);
    }

    @PostMapping
    public CategoryResponseDto createCategory(@RequestBody CategoryRequestDto categoryDto, @RequestHeader(value = "Authorization", required = false) String token) {
        if(token == null || token.isEmpty()){
            throw new NoAuthenticationException();
        }
        String userRole = jwtTokenUtil.extractUserRole(token);
        if(userRole.equals("USER")) {
            throw new NoAccessException();
        }
        return categoryService.createCategory(categoryDto,token);
    }

    @PutMapping("/{id}")
    public CategoryResponseDto updateCategory(@PathVariable Long id,@RequestBody CategoryRequestDto categoryDto, @RequestHeader(value = "Authorization", required = false) String token) {
        if(token == null || token.isEmpty()){
            throw new NoAuthenticationException();
        }
        String userRole = jwtTokenUtil.extractUserRole(token);
        if(userRole.equals("USER")) {
            throw new NoAccessException();
        }

        return categoryService.updateCategory(id,categoryDto);
    }

    @DeleteMapping("/{id}")
    public String deleteCategory(@PathVariable Long id, @RequestHeader(value = "Authorization", required = false) String token) {
        if(token == null || token.isEmpty()){
            throw new NoAuthenticationException();
        }
        String userRole = jwtTokenUtil.extractUserRole(token);
        if(userRole.equals("USER")) {
            throw new NoAccessException();
        }
        return categoryService.deleteCategory(id);
    }
}
