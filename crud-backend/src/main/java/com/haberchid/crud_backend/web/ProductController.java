package com.haberchid.crud_backend.web;

import com.haberchid.crud_backend.dto.ProductDto;
import com.haberchid.crud_backend.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product")
public class ProductController {

    private final ProductService productService;

    @PostMapping("save")
    public ResponseEntity<ProductDto> saveProduct(@RequestBody ProductDto productDto){
        var result = productService.saveProduct(productDto);
        if (result != null)
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        else
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable String id, @RequestBody ProductDto productDto){
        var result = productService.updateProduct(id, productDto);
        if (result != null)
            return new ResponseEntity<>(result, HttpStatus.OK);
        else
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ProductDto> deleteProduct(@PathVariable String id){
        var result = productService.deleteProduct(id);
        if (result != null)
            return new ResponseEntity<>(result, HttpStatus.OK);
        else
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<ProductDto> getOneProduct(@PathVariable String id){
        var result = productService.getOneProduct(id);
        if (result != null)
            return new ResponseEntity<>(result, HttpStatus.OK);
        else
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/products")
    public ResponseEntity<List<ProductDto>> getAllProduct(){
        return new ResponseEntity<>(productService.allProducts(), HttpStatus.OK);
    }
}
