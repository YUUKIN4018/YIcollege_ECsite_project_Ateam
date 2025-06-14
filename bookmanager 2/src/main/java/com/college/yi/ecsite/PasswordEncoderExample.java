package com.college.yi.ecsite;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderExample {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "asdf1234";  // 平文パスワード
        String encodedPassword = encoder.encode(rawPassword);
        System.out.println("ハッシュ化パスワード: " + encodedPassword);
    }
}
