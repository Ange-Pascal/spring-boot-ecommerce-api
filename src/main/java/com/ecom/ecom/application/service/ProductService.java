package com.ecom.ecom.application.service;

import com.ecom.ecom.application.dto.ProductRequest;
import com.ecom.ecom.application.dto.ProductResponse;
import com.ecom.ecom.application.model.Product;
import com.ecom.ecom.application.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    public ProductResponse createProduct(ProductRequest productRequest) {
        Product product = new Product();
        updateProductFromRequest (product, productRequest);
        Product savedProduct = productRepository.save(product);
        return  mapToProductResponse(savedProduct);
    }

    private ProductResponse mapToProductResponse(Product savedProduct) {
        ProductResponse response = new ProductResponse();
        response.setId(savedProduct.getId());
        response.setName(savedProduct.getName());
        response.setDescription(savedProduct.getDescription());
        response.setPrice(savedProduct.getPrice());
        response.setStockQuantity(savedProduct.getStockQuantity());
        response.setCategory(savedProduct.getCategory());
        response.setImageUrl(savedProduct.getImageUrl());
        return response;
    }

    private void updateProductFromRequest(Product product, ProductRequest productRequest) {

    }
}
