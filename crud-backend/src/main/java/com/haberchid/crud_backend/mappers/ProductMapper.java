package com.haberchid.crud_backend.mappers;

import com.haberchid.crud_backend.dto.ProductDto;
import com.haberchid.crud_backend.entitys.Product;
import lombok.Builder;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public Product dtoToProduct(ProductDto productDto){
        return Product.builder()
                .productId(productDto.productId())
                .title(productDto.title())
                .description(productDto.description())
                .category(productDto.category())
                .createdAt(productDto.createdAt())
                .updatedAt(productDto.updatedAt())
                .user(productDto.user())
                .build();
    }

    public ProductDto productToDto(Product product){
        return ProductDto.builder()
                .productId(product.getProductId())
                .title(product.getTitle())
                .description(product.getDescription())
                .category(product.getCategory())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .user(product.getUser())
                .build();
    }
}
