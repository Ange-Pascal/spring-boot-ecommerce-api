package com.ecom.ecom.application.repository;

import com.ecom.ecom.application.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
