package com.college.yi.ecsite.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Order {
	private Long id;
	private Long userId;
	private LocalDateTime orderDate;
	private BigDecimal totalAmount;

}
