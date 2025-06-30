package com.college.yi.ecsite.front.controller;

import jakarta.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    private final LoginService loginService;

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("loginForm", new LoginForm());
        logger.info("ログインフォーム表示");
        return "front/login";
    }

    @PostMapping("/login")
    public String doLogin(
        LoginForm form,
        Model model,
        HttpSession session
    ) {
        logger.info("ログイン処理開始: email={}", form.getEmail());

        try {
            User user = loginService.authenticate(form.getEmail(), form.getPassword());
            session.setAttribute("loginUser", user);
            logger.info("ログイン成功: user_id={}", user.getUserId());
            return "redirect:/mypage";
        } catch (AuthenticationException ex) {
            logger.warn("ログイン失敗: {}", ex.getMessage());
            model.addAttribute("loginError", ex.getMessage());
            return "front/login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        logger.info("ログアウト完了");
        return "redirect:/login";
    }
}

//package com.college.yi.ecsite.front.controller;
//
//import jakarta.servlet.http.HttpSession;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//
//import com.college.yi.ecsite.entity.User;
//import com.college.yi.ecsite.front.exception.AuthenticationException;
//import com.college.yi.ecsite.front.form.LoginForm;
//import com.college.yi.ecsite.front.service.LoginService;
//
//import lombok.RequiredArgsConstructor;
//
//@Controller
//@RequiredArgsConstructor
//public class LoginController {
//
//    private final LoginService loginService;
//
//    @GetMapping("/login")
//    public String showLoginForm(Model model) {
//        model.addAttribute("loginForm", new LoginForm());
//        return "front/login";
//    }
//
//    @PostMapping("/login")
//    public String doLogin(
//        LoginForm form,
//        Model model,
//        HttpSession session
//    ) {
//        try {
//            User user = loginService.authenticate(form.getEmail(), form.getPassword());
//            session.setAttribute("loginUser", user);
//            return "redirect:/mypage";
//        } catch (AuthenticationException ex) {
//            model.addAttribute("loginError", ex.getMessage());
//            return "front/login";
//        }
//    }
//
//
//
//    @GetMapping("/logout")
//    public String logout(HttpSession session) {
//        session.invalidate();  // セッション破棄でログアウト
//        return "redirect:/login";
//    }
//}