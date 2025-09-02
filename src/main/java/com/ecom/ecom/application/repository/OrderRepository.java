package com.ecom.ecom.application.repository;

import com.ecom.ecom.application.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
