package com.college.yi.ecsite.front.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.college.yi.ecsite.entity.User;
import com.college.yi.ecsite.front.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MyPageController {

    private final UserService userService;

    @GetMapping("/mypage")
    public String showMyPage(Model model, Principal principal) {
        String email = principal.getName();  // ログインユーザーのメールアドレス取得
        User user = userService.findByEmail(email);  // DBからユーザー情報取得
        model.addAttribute("user", user);  // 画面にユーザー情報を渡す
        return "front/mypage";
    }
}
