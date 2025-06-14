package com.college.yi.ecsite.front.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.college.yi.ecsite.entity.User;
import com.college.yi.ecsite.front.exception.AuthenticationException;
import com.college.yi.ecsite.front.form.LoginForm;
import com.college.yi.ecsite.front.service.LoginService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @GetMapping("/login")
    public String showLoginForm(LoginForm form) {
        return "front/login";
    }

    @PostMapping("/login")
    public String doLogin(@Valid LoginForm form, BindingResult bindingResult, Model model, HttpSession session) {
        if (bindingResult.hasErrors()) {
            return "front/login";
        }

        try {
            User user = loginService.authenticate(form.getEmail(), form.getPassword());
            session.setAttribute("loginUser", user);  // ここでセッションにユーザー情報を保存
            return "redirect:/mypage";  // ログイン成功時のリダイレクト先
        } catch (AuthenticationException ex) {
            model.addAttribute("loginError", ex.getMessage());
            return "front/login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();  // セッション破棄でログアウト
        return "redirect:/login";
    }
}
