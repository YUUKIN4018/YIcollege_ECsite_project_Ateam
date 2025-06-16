package com.college.yi.ecsite.front.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.college.yi.ecsite.entity.Product;

@Mapper
public interface ProductMapper {
    Product findById(Long id);
    int updateStock(@Param("id") Long id, @Param("quantity") int quantity);
	void updateStock(Long productId, long l);
}
