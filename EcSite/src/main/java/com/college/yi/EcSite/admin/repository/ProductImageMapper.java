package com.college.yi.EcSite.admin.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.college.yi.EcSite.entity.ProductImage;

@Mapper
public interface ProductImageMapper{
    List<ProductImage> findByProductIds(List<Long> productIds);
    void insert(ProductImage productImage);
}
