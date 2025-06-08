package com.college.yi.ecsite.admin.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.college.yi.ecsite.admin.form.AdminLoginForm;
import com.college.yi.ecsite.admin.service.AdminLoginService;
import com.college.yi.ecsite.entity.User;

@Controller
@RequestMapping("/admin")
public class AdminLoginController {

    @Autowired
    private AdminLoginService adminLoginService;
    
    @Autowired
    private HttpSession session;
    

    @GetMapping("/login")
    public String showLoginForm(AdminLoginForm form, @RequestParam(value = "logout", required = false) String logout,Model model) {
        
        if(logout != null) {
            model.addAttribute("logoutMessage", "ログアウトしました");
        }
        
        return "admin/login";
    }

    @PostMapping("/login")
    public String login(@Valid AdminLoginForm adminLoginForm, BindingResult bindingResult, Model model) {

        // 入力チェックでエラーがあればログイン画面に戻す
        if (bindingResult.hasErrors()) {
            return "admin/login";
        }

        try {
            // メールアドレスとパスワードでログイン認証
            User admin = adminLoginService.authenticator(adminLoginForm.getEmail(), adminLoginForm.getPassword());
            //セッションに保存
            session.setAttribute("adminUser", admin);
            return "redirect:/admin/dashboard";
        } catch (Exception e) {
            // 認証失敗時、エラーメッセージを表示してログイン画面に戻す
            model.addAttribute("loginError", e.getMessage());
            return "admin/login";
        }

    }
    
}
