package com.college.yi.ecsite.front.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.college.yi.ecsite.entity.User;

@Mapper
public interface UserMapper {
    User findByEmail(@Param("email") String email);
}
