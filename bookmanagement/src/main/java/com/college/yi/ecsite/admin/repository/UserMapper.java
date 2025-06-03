package com.college.yi.ecsite.admin.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.college.yi.ecsite.entity.User;

@Mapper
public interface UserMapper  {

    @Select("SELECT * FROM users WHERE role = 'ADMIN' AND email = #{email}")
    User findAdminByEmail(String email);
}
