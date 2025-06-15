package com.college.yi.ecsite.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.college.yi.ecsite.admin.dto.AdminDashboardDto;
import com.college.yi.ecsite.admin.service.AdminDashboardService;

@Controller
@RequestMapping("/admin")
public class AdminDashboardController {

    @Autowired
    private AdminDashboardService adminDashboardService;
    
    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
        AdminDashboardDto dashboardData = adminDashboardService.getDashBoardData();
        model.addAttribute("dashboardData", dashboardData);
        return "admin/dashboard";
    }
}
