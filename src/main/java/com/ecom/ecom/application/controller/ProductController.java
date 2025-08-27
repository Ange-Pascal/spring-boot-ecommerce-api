package com.ecom.ecom.application.controller;

import com.ecom.ecom.application.dto.ProductRequest;
import com.ecom.ecom.application.dto.ProductResponse;
import com.ecom.ecom.application.model.Product;
import com.ecom.ecom.application.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products/")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest productRequest){
        return  new ResponseEntity<ProductResponse>(productService.createProduct(productRequest), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ProductResponse> getProducts(){
        return  ResponseEntity.ok(productService.getAllProducts());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable Long id, @RequestBody ProductRequest productRequest){
        return  productService.updateProduct(id, productRequest)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
