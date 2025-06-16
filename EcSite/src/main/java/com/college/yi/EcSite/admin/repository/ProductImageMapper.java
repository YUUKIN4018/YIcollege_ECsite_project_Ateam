package com.college.yi.EcSite.admin.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import com.college.yi.EcSite.entity.ProductImage;

@Mapper
public interface ProductImageMapper {
    
    List<ProductImage> findByProductIds(@Param("productIds") List<Long> productIds);

    
    List<ProductImage> findByProductId(@Param("productId") Long productId);

   
    void deleteByProductId(@Param("productId") Long productId);

    
    void insert(ProductImage productImage);
}
