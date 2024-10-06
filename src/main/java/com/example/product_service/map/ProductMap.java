package com.example.product_service.map;

import com.example.product_service.dto.ProductDto;
import com.example.product_service.model.Product;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface ProductMap {

    ProductDto toProductDto(Product product);
    Product toEntity(ProductDto productDto);
    List<ProductDto> toDto(List<Product> products);

}
