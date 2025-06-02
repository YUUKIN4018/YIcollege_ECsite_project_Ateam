package com.college.yi.ecsite.front.dto;

import lombok.Data;

@Data
public class TopPageProductDto {
    private Integer productId;
    private String name;
    private Integer price;
    private String thumbnailUrl;
}