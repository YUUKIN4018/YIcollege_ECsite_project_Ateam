package com.college.yi.ecsite.front.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.college.yi.ecsite.entity.User;
import com.college.yi.ecsite.front.exception.UserNotFoundException;
import com.college.yi.ecsite.front.repository.UserMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PasswordResetService {

    private final UserMapper userMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    @Transactional
    public void resetPassword(String email, String newPassword, String confirmPassword) {

        User user = userMapper.findByEmail(email);
        if (user == null) {
            throw new UserNotFoundException("該当するメールアドレスが登録されていません");
        }

        if (!newPassword.equals(confirmPassword)) {
            throw new IllegalArgumentException("新しいパスワードと確認用パスワードが一致しません");
        }

        String passwordHash = passwordEncoder.encode(newPassword);

        userMapper.updatePassword(email, passwordHash);
    }
}
