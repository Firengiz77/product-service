package com.example.product_service.service;

import com.example.product_service.dto.request.ProductRequestDto;
import com.example.product_service.dto.response.ProductResponseDto;
import com.example.product_service.exception.CategoryNotFoundException;
import com.example.product_service.exception.ProductNotFoundException;
import com.example.product_service.map.ProductMap;
import com.example.product_service.model.Image;
import com.example.product_service.model.Product;
import com.example.product_service.repository.CategoryRepository;
import com.example.product_service.repository.ProductRepository;
import com.example.product_service.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMap productMap;
    private final ImageService imageService;
    private final JwtTokenUtil jwtTokenUtil;


    public List<ProductResponseDto> getProducts() {
        return productMap.toDto(productRepository.findAll());
    }

    public ProductResponseDto getProduct(Long id) {
        return productMap.toProductDto(productRepository.findById(id).orElseThrow(()-> new ProductNotFoundException()));
    }

    public ProductResponseDto createProduct(ProductRequestDto productDto,String token) {

        String userId = jwtTokenUtil.extractUserId(token);
        Image image1 = imageService.create(productDto.getImage());
        Product product = Product.builder()
                .name(productDto.getName())
                .price(productDto.getPrice())
                .description(productDto.getDescription())
                .image(image1)
                .category(categoryRepository.findById(productDto.getCategory()).orElseThrow(()->new CategoryNotFoundException()))
                .userId(userId)
                .build();
        return productMap.toProductDto(productRepository.save(product));
    }

    public ProductResponseDto updateProduct(Long id,ProductRequestDto productDto) throws IOException {
        Product product = productRepository.findById(id).orElseThrow(()-> new ProductNotFoundException());
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());
        Image image1 = imageService.update(product.getImage().getId(),productDto.getImage());
        product.setImage(image1);
        product.setCategory(categoryRepository.findById(productDto.getCategory()).orElseThrow(()->new CategoryNotFoundException()));
        return productMap.toProductDto(productRepository.save(product));
    }

    public String deleteProduct(Long id) {
        productRepository.deleteById(id);
        return "Product deleted";
    }
}