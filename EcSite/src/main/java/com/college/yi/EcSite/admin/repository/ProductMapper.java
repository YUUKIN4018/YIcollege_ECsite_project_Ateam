package com.college.yi.EcSite.admin.repository;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.college.yi.EcSite.entity.Product;

@Mapper
public interface ProductMapper {
    List<Product> findAll();
    Optional<Product> findById(Long productId);
    void insert(Product product);
    void update(Product product); 
}

