package com.example.product_service.service;

import com.example.product_service.dto.request.ProductRequestDto;
import com.example.product_service.dto.response.ProductResponseDto;
import com.example.product_service.exception.CategoryNotFoundException;
import com.example.product_service.exception.ProductNotFoundException;
import com.example.product_service.map.ProductMap;
import com.example.product_service.model.Product;
import com.example.product_service.repository.CategoryRepository;
import com.example.product_service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMap productMap;

    public List<ProductResponseDto> getProducts() {
       return productMap.toDto(productRepository.findAll());
    }

    public ProductResponseDto getProduct(Long id) {
        return productMap.toProductDto(productRepository.findById(id).orElseThrow(()-> new ProductNotFoundException()));
    }

    public ProductResponseDto createProduct(ProductRequestDto productDto) {
        Product product = Product.builder()
                .name(productDto.getName())
                .price(productDto.getPrice())
                .description(productDto.getDescription())
                .image(productDto.getImage())
                .category(categoryRepository.findById(productDto.getCategory()).orElseThrow(()->new CategoryNotFoundException()))
                .build();
        return productMap.toProductDto(productRepository.save(product));
    }

    public ProductResponseDto updateProduct(Long id,ProductRequestDto productDto) {
        Product product = productRepository.findById(id).orElseThrow(()-> new ProductNotFoundException());
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());
        product.setImage(productDto.getImage());
        product.setCategory(categoryRepository.findById(productDto.getCategory()).orElseThrow(()->new CategoryNotFoundException()));
        return productMap.toProductDto(productRepository.save(product));
    }

    public String deleteProduct(Long id) {
        productRepository.deleteById(id);
        return "Product deleted";
    }
}
