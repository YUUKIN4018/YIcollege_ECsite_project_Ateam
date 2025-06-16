package com.college.yi.ecsite.entity;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class Product {
	private Long id;
    private Long orderId;
    private Long productId;
    private String name;        // 商品名
    private String imageUrl;    // 商品画像のURL
    private int quantity;
    private BigDecimal price;   // 商品の単価
    private int stock;          // 在庫数

}
