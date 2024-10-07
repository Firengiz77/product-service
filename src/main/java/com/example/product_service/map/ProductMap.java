package com.example.product_service.map;

import com.example.product_service.dto.response.ProductResponseDto;
import com.example.product_service.model.Product;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface ProductMap {

    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "image", target = "image")
    @Mapping(source = "category", target = "category")
    ProductResponseDto toProductDto(Product product);
    Product toEntity(ProductResponseDto productDto);
    List<ProductResponseDto> toDto(List<Product> products);

}
