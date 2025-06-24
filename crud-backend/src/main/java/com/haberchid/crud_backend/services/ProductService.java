package com.haberchid.crud_backend.services;

import com.haberchid.crud_backend.dto.ProductDto;

import java.util.List;

public interface ProductService {

    ProductDto saveProduct(ProductDto productDto);
    ProductDto updateProduct(String id, ProductDto productDto);
    ProductDto deleteProduct(String id);
    ProductDto getOneProduct(String id);
    List<ProductDto> allProducts();

}
