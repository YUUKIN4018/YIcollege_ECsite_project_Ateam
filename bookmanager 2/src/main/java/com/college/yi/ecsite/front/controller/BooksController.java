package com.college.yi.ecsite.front.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import ch.qos.logback.core.model.Model;

@Controller
public class BooksController {

    @GetMapping("/top")
    public String showBooksPage(Model model) {
        // ここに本の一覧取得などを書く（今は仮に固定でもOK）
        return "front/top"; // → templates/books.html が必要
    }
}