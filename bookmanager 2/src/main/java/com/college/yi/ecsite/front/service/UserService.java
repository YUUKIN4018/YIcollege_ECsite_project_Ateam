package com.college.yi.ecsite.front.service;

import org.springframework.stereotype.Service;

import com.college.yi.ecsite.entity.User;
import com.college.yi.ecsite.front.repository.UserMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;

    public User findByEmail(String email) {
        return userMapper.findByEmail(email);
    }
}
