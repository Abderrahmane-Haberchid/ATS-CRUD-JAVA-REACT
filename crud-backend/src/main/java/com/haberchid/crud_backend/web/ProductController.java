package com.haberchid.crud_backend.web;

import com.haberchid.crud_backend.dto.ProductDto;
import com.haberchid.crud_backend.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product")
public class ProductController {

    private final ProductService productService;

    @PostMapping("save")
    public ResponseEntity<ProductDto> saveProduct(@RequestBody ProductDto productDto){
        var result = productService.saveProduct(productDto);
        if (result != null)
            return new ResponseEntity<>(HttpStatus.CREATED);
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
