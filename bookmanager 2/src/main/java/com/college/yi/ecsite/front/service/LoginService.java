package com.college.yi.ecsite.front.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.college.yi.ecsite.entity.User;
import com.college.yi.ecsite.front.exception.AuthenticationException;
import com.college.yi.ecsite.front.repository.UserMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginService {

    private static final Logger logger = LoggerFactory.getLogger(LoginService.class);

    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public User authenticate(String email, String rawPassword) {
        logger.info("authenticate called with email='{}', rawPassword length={}", email, rawPassword == null ? "null" : rawPassword.length());

        if (email == null || email.isBlank()) {
            logger.warn("メールアドレスが空です");
            throw new AuthenticationException("メールアドレスを入力してください");
        }
        if (email.length() > 100) {
            logger.warn("メールアドレスが長すぎます（{}文字）", email.length());
            throw new AuthenticationException("メールアドレスは100文字以内で入力してください");
        }

        if (rawPassword == null || rawPassword.isBlank()) {
            logger.warn("パスワードが空です");
            throw new AuthenticationException("パスワードを入力してください");
        }
        if (rawPassword.length() < 8) {
            logger.warn("パスワードが短すぎます（{}文字）", rawPassword.length());
            throw new AuthenticationException("パスワードは8文字以上で入力してください");
        }

        logger.info("入力チェック完了。DBからユーザー情報を取得します。");
        User user = userMapper.findByEmail(email);

        if (user == null) {
            logger.warn("ユーザーが見つかりません。email='{}'", email);
            throw new AuthenticationException("メールアドレスまたはパスワードが間違っています");
        }

        if (!passwordEncoder.matches(rawPassword, user.getPasswordHash())) {
            logger.warn("パスワード不一致 email='{}'", email);
            throw new AuthenticationException("メールアドレスまたはパスワードが間違っています");
        }

        logger.info("認証成功。user_id={}", user.getUserId());
        return user;
    }
}
