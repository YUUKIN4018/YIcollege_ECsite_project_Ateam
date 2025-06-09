package com.college.yi.ecsite.front.Entity;

import lombok.Data;

@Data
public class Product {
    private Integer productId;
    private String name;
    private Integer price;
    private String thumbnailUrl;
    private String category;
    private Boolean featured;
}