package com.college.yi.EcSite.admin.dto;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

import com.college.yi.EcSite.entity.Product;
import com.college.yi.EcSite.entity.ProductImage;

import lombok.Data;

@Data
public class ProductEditDto {
    private Long productId;
    private String name;
    private Integer price;
    private String description;
    private Integer stockQuantity;
    private Integer status;
    private List<ProductImageDto> images;
    
    public static ProductEditDto of(Product product, List<ProductImage> images) {
        ProductEditDto dto = new ProductEditDto();

        BeanUtils.copyProperties(product, dto);
        
        List<ProductImageDto> imageDtos = images.stream()
                .map((ProductImage img) -> ProductImageDto.of(img))
                .collect(Collectors.toList());  
            dto.setImages(imageDtos);

            return dto;
        }
    

    

}
