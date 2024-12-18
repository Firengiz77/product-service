package com.example.product_service.controller;

import com.example.product_service.dto.request.ProductRequestDto;
import com.example.product_service.dto.response.ProductResponseDto;
import com.example.product_service.exception.NoAccessException;
import com.example.product_service.exception.NoAuthenticationException;
import com.example.product_service.service.ProductService;
import com.example.product_service.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final JwtTokenUtil jwtTokenUtil;

    @GetMapping("/admin")
    public List<ProductResponseDto> getAdminProducts(@RequestHeader(value = "Authorization", required = false) String token) {
        if(token == null || token.isEmpty()){
            throw new NoAuthenticationException();
        }
        return productService.getAdminProducts();
    }

    @GetMapping
    public List<ProductResponseDto> getUserProducts() {
        return productService.getUserProducts();
    }

    @GetMapping("/admin/{id}")
    public ProductResponseDto getAdminProduct(@PathVariable Long id,@RequestHeader(value = "Authorization", required = false)  String token) {
        if(token == null || token.isEmpty()){
            throw new NoAuthenticationException();
        }
        return productService.getAdminProduct(id);
    }

    @GetMapping("/{id}")
    public ProductResponseDto getUserProduct(@PathVariable Long id) {
        return productService.getUserProduct(id);
    }

    @PostMapping
    public ProductResponseDto createProduct(ProductRequestDto productDto, @RequestHeader(value = "Authorization", required = false) String token) {

        if(token == null || token.isEmpty()){
            throw new NoAuthenticationException();
        }
        String userRole = jwtTokenUtil.extractUserRole(token);
        if(userRole.equals("USER")) {
            throw new NoAccessException();
        }
        return productService.createProduct(productDto,token);
    }

    @PutMapping("/{id}")
    public ProductResponseDto updateProduct(@PathVariable Long id,ProductRequestDto productDto, @RequestHeader(value = "Authorization", required = false) String token) throws IOException {
        if(token == null || token.isEmpty()){
            throw new NoAuthenticationException();
        }
        String userRole = jwtTokenUtil.extractUserRole(token);
        if(userRole.equals("USER")) {
            throw new NoAccessException();
        }
        return productService.updateProduct(id,productDto);
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id, @RequestHeader(value = "Authorization", required = false) String token) {
        if(token == null || token.isEmpty()){
            throw new NoAuthenticationException();
        }
        String userRole = jwtTokenUtil.extractUserRole(token);
        if(userRole.equals("USER")) {
            throw new NoAccessException();
        }
        return productService.deleteProduct(id);
    }
}