package com.college.yi.ecsite.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Order {

    private Long orderId;
    private Long userId;
    private BigDecimal totalAmount;
    private BigDecimal shippingFee;
    private BigDecimal taxAmount;
    private Long paymentMethodId;
    private Long addressId;
    private Short addressMethods;
    private Long notes;
    private Short orderStatus;
    private Short paymentStatus;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
