package com.example.product_service.service;

import com.example.product_service.dto.ProductDto;
import com.example.product_service.exception.ProductNotFoundException;
import com.example.product_service.map.ProductMap;
import com.example.product_service.model.Product;
import com.example.product_service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMap productMap;

    public List<ProductDto> getProducts() {
       return productMap.toDto(productRepository.findAll());
    }

    public ProductDto getProduct(Long id) {
        return productMap.toProductDto(productRepository.findById(id).orElseThrow(()-> new ProductNotFoundException()));
    }

    public ProductDto createProduct(ProductDto productDto) {
        Product product = Product.builder()
                .name(productDto.getName())
                .price(productDto.getPrice())
                .description(productDto.getDescription())
                .image(productDto.getImage())
                .build();
        return productMap.toProductDto(productRepository.save(product));
    }

    public ProductDto updateProduct(Long id,ProductDto productDto) {
        Product product = productRepository.findById(id).orElseThrow(()-> new ProductNotFoundException());
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());
        product.setImage(productDto.getImage());
        return productMap.toProductDto(productRepository.save(product));
    }

    public String deleteProduct(Long id) {
        productRepository.deleteById(id);
        return "Product deleted";
    }
}
