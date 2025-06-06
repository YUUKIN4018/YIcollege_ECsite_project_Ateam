package com.college.yi.EcSite.admin.dto;

import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductImageDto {
    
    @NonNull
    private Long imageId;
    @Size(max = 255)
    private String imageUrl;
    private Integer sortOrder;
    private Boolean isMan;
    private Long productId;

}
