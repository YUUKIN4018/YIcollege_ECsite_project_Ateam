package com.college.yi.EcSite.admin.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductsDto {
    private Long productId;
    private String name;
    private String description;
    private BigDecimal price;
    private BigDecimal taxRate;
    private Integer stockQuantity;
    private Integer status;
    private List<ProductImageDto> images;
    
    private CategoryDto category;

}
