package com.college.yi.ecsite.front.service;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.college.yi.ecsite.entity.User;
import com.college.yi.ecsite.front.exception.AuthenticationException;
import com.college.yi.ecsite.front.repository.UserMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public User authenticate(String email, String rawPassword) {
        User user = userMapper.findByEmail(email);

        if (user == null || !passwordEncoder.matches(rawPassword, user.getPasswordHash())) {
            throw new AuthenticationException("メールアドレスまたはパスワードが間違っています");
        }

        return user;
    }
}
