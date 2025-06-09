package com.college.yi.ecsite.front.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.college.yi.ecsite.front.dto.TopPageProductDto;
import com.college.yi.ecsite.front.service.TopPageService;

@Controller
public class TopPageController {
    private final TopPageService topPageService;

    public TopPageController(TopPageService topPageService) {
        this.topPageService = topPageService;
    }

    @GetMapping("/")
    public String showTopPage(@RequestParam(defaultValue = "1") int page, Model model) {
        List<TopPageProductDto> products = topPageService.getTopProducts(page);
        model.addAttribute("products", products);
        return "front/top"; // HTMLテンプレート（例：templates/front/top.html）
    }

    @GetMapping("/search")
    public String search(@RequestParam String keyword, Model model) {
        List<TopPageProductDto> products = topPageService.searchProducts(keyword);
        model.addAttribute("products", products);
        return "front/top";
    }

    @GetMapping("/category")
    public String filter(@RequestParam String category, Model model) {
        List<TopPageProductDto> products = topPageService.filterByCategory(category);
        model.addAttribute("products", products);
        return "front/top";
    }
}