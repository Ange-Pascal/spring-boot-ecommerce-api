package com.ecom.ecom.application.repository;

import com.ecom.ecom.application.model.CartItem;
import com.ecom.ecom.application.model.Product;
import com.ecom.ecom.application.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem,Long> {
    CartItem findByUserAndProduct(User user, Product product);

    void deleteByUserAndProduct(User user, Product product);

}
