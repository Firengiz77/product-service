package com.example.product_service.map;

import com.example.product_service.dto.response.ProductResponseDto;
import com.example.product_service.model.Image;
import com.example.product_service.model.Product;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface ProductMap {

    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "image", target = "image", qualifiedByName = "extractImageName")
    @Mapping(source = "category", target = "category")
    ProductResponseDto toProductDto(Product product);

    @Mapping(source = "image", target = "image", ignore = true)
    Product toEntity(ProductResponseDto productDto);
    List<ProductResponseDto> toDto(List<Product> products);

    @Named("extractImageName")
    default String extractImageName(Image image) {
        return image != null ? image.getName() : null;
    }

}
