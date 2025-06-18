package com.haberchid.crud_backend.services;

import com.haberchid.crud_backend.dto.ProductDto;

public interface ProductService {

    ProductDto saveProduct(ProductDto productDto);
}
