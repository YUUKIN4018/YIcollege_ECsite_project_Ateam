package com.college.yi.ecsite.admin.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.college.yi.ecsite.admin.form.AdminLoginForm;
import com.college.yi.ecsite.admin.service.AdminLoginService;

import ch.qos.logback.core.model.Model;

@Controller
@RequestMapping("/admin")
public class AdminlogoutController {

    @Autowired
    private AdminLoginService adminLoginService;
    
    @Autowired
    private HttpSession session;
    
    @GetMapping("/logout/confirm")
    public String logout(@Valid AdminLoginForm adminLoginForm, BindingResult bindingResult, Model model ) {
        return "admin/dashboard";
        
    }
    
}
