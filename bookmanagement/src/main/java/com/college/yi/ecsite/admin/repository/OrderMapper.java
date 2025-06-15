package com.college.yi.ecsite.admin.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface OrderMapper {

    @Select("SELECT COUNT(*) FROM orders")
    int countOrders();
}
