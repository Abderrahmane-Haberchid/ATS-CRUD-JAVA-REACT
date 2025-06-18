package com.haberchid.crud_backend.services.Impl;


import com.haberchid.crud_backend.dto.ProductDto;
import com.haberchid.crud_backend.mappers.ProductMapper;
import com.haberchid.crud_backend.repositories.ProductRepository;
import com.haberchid.crud_backend.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public ProductDto saveProduct(ProductDto productDto) {
        var productEntity = productMapper.dtoToProduct(productDto);
        var product = productRepository.save(productEntity);
        return productMapper.productToDto(product);
    }
}
