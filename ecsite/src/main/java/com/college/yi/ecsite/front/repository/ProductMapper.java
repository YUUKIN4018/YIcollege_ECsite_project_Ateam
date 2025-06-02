package com.college.yi.ecsite.front.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.college.yi.ecsite.front.dto.TopPageProductDto;

@Mapper
public interface ProductMapper {
    List<TopPageProductDto> findAll(@Param("limit") int limit, @Param("offset") int offset);
    List<TopPageProductDto> searchByKeyword(@Param("keyword") String keyword);
    List<TopPageProductDto> filterByCategory(@Param("category") String category);
}