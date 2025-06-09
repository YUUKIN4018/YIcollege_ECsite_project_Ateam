package com.college.yi.ecsite.entity;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class OderItem {
	private Long id;
	private Long orderId;
	private Long priductId;
	private int quantity;
	private BigDecimal price;

}
