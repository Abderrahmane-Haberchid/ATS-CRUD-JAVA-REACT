package com.haberchid.crud_backend.services.Impl;


import com.haberchid.crud_backend.dto.ProductDto;
import com.haberchid.crud_backend.entitys.Product;
import com.haberchid.crud_backend.mappers.ProductMapper;
import com.haberchid.crud_backend.repositories.ProductRepository;
import com.haberchid.crud_backend.repositories.UserRepository;
import com.haberchid.crud_backend.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

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

    @Override
    public ProductDto updateProduct(String id, ProductDto productDto) {
        var product = productRepository.findById(UUID.fromString(id)).orElseThrow();
            product.setTitle(productDto.title());
            product.setDescription(productDto.description());
            product.setCategory(productDto.category());
            product.setUpdatedAt(new Date());
        var savedProduct = productRepository.save(product);
        return productMapper.productToDto(savedProduct);
    }

    @Override
    public ProductDto deleteProduct(String id) {
        var product = productRepository.findById(UUID.fromString(id)).orElseThrow();
        if (product != null)
            productRepository.deleteById(UUID.fromString(id));

        return productMapper.productToDto(product);
    }

    @Override
    public ProductDto getOneProduct(String id) {
        var product = productRepository.findById(UUID.fromString(id)).orElseThrow();
        return productMapper.productToDto(product);
    }

    @Override
    public List<ProductDto> allProducts() {
        List<Product> productList = productRepository.findAll();
        return productMapper.productListToDto(productList);
    }
}
