package com.college.yi.ecsite.entity;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class CartItem {
    private Long id;
    private Long orderId;
    private Long userId;
    private Long productId;
    private int quantity;
    private BigDecimal price;

}
