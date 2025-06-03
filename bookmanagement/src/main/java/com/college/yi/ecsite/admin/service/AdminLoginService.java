package com.college.yi.ecsite.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.college.yi.ecsite.admin.repository.UserMapper;
import com.college.yi.ecsite.entity.User;
import com.college.yi.ecsite.exception.AuthenticationException;

@Service
public class AdminLoginService {

    @Autowired
    private UserMapper userMapper;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public User authenticator(String email, String rawPassword)  {
        
        //メールアドレスに該当するユーザー情報を取得
        User user = userMapper.findAdminByEmail(email);
        
        //ユーザーが存在しない
        if (user == null ) {
            throw new AuthenticationException("ユーザーが存在しません。");
        }
        
        //パスワードが一致しない
        boolean pssswordIsCorrrect = passwordEncoder.matches(rawPassword, user.getPassword_hash());
        if (!pssswordIsCorrrect) {
            throw new AuthenticationException("パスワードが一致しません。");
        }
        
        //管理者権限がない
        String role = user.getRole();
        
        boolean isAdmin = "ADMIN".equals(role);
        
        if (!isAdmin) {
            throw new AuthenticationException("管理者権限がありません");
        }
        
        return user;
    }
        
}
