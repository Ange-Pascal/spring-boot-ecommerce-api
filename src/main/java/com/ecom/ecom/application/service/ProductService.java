package com.ecom.ecom.application.service;

import com.ecom.ecom.application.dto.ProductRequest;
import com.ecom.ecom.application.dto.ProductResponse;
import com.ecom.ecom.application.model.Product;
import com.ecom.ecom.application.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
        response.setActive(savedProduct.getActive());
        response.setPrice(savedProduct.getPrice());
        response.setStockQuantity(savedProduct.getStockQuantity());
        response.setCategory(savedProduct.getCategory());
        response.setImageUrl(savedProduct.getImageUrl());
        return response;
    }

    private void updateProductFromRequest(Product product, ProductRequest productRequest) {

        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());
        product.setStockQuantity(productRequest.getStockQuantity());
        product.setCategory(productRequest.getCategory());
        product.setImageUrl(productRequest.getImageUrl());


    }

    public Optional<ProductResponse> updateProduct(Long id, ProductRequest productRequest) {
       return productRepository.findById(id)
                .map(existingProduct ->{
                    updateProductFromRequest(existingProduct, productRequest);
                   Product saveProduct = productRepository.save(existingProduct);
                    return mapToProductResponse(saveProduct);
                });
    }

    public ProductResponse getAllProducts() {

    }
}
