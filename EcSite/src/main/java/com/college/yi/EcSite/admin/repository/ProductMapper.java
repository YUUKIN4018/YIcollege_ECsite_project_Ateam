package com.college.yi.EcSite.admin.repository;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import com.college.yi.EcSite.entity.Product;

@Mapper
public interface ProductMapper {
    List<Product> findAll();
    Optional<Product> findById(Long productId);
    void insert(Product product);
    void update(Product product); 
    int updateStock(@Param("productId") Long productId, @Param("stockQuantity") Integer stockQuantity);
}

