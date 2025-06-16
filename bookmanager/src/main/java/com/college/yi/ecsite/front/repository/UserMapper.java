package com.college.yi.ecsite.front.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.college.yi.ecsite.entity.User;

@Mapper
public interface UserMapper {
	
	@Insert("INSERT INTO users(last_name , first_name , last_name_kana , first_name_kana , email, password_hash, created_at) VALUES(#{lastName}, #{firstName}, #{lastNameKana}, #{firstNameKana}, #{email}, #{password}, #{createdAt})")
	void insert(User user);

	@Select("SELECT * FROM users WHERE email = #{email}")
	User findByEmail(String email);
}
