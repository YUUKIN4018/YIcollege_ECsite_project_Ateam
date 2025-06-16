package com.college.yi.ecsite.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminlogoutController {
    
    //確認画面の表示
    @GetMapping("/logout/confirm")
    public String showlogoutPage() {
        return "admin/logout-confirm";
        
    }
    
    //キャンセルボタン押下時の処理
    @GetMapping("/logout/cancel")
    public String cancelLogout() {
        return "redirect:/admin/dashboard";
    }
        
}
