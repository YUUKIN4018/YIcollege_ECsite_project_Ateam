package com.college.yi.ecsite.admin.service;

import org.springframework.stereotype.Service;

import com.college.yi.ecsite.admin.repository.UserMapper;
import com.college.yi.ecsite.entity.User;

@Service
public class UserService {

    private final UserMapper userMapper;

    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public User findByEmail(String email) {
        return userMapper.findByEmail(email);
    }
}