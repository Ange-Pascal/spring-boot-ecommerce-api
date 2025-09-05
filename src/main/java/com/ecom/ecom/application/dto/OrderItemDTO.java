package com.ecom.ecom.application.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItemDTO {
    private Long id;
    private  Long productId;
    private Integer quantity;
    private BigDecimal price;
}
