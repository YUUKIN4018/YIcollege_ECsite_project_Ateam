package com.college.yi.ecsite.admin.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ProductMapper {

    @Select("SELECT COUNT(*) FROM products")
    int countProducts();
    
    @Select("SELECT COUNT(*) FROM products WHERE stockQuantity = 0")
    int countOutOfStockProducts();

}
