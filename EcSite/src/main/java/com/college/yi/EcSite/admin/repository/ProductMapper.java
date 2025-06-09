package com.college.yi.EcSite.admin.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.college.yi.EcSite.entity.Product;

@Mapper
public interface ProductMapper {
    List<Product>findAll();
    void insert(Product product);

}
