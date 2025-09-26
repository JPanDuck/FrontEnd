package com.example.realrealfinal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RootController {

    // 루트(/) 접속 시 로그인 페이지로 리다이렉트
    @GetMapping("/")
    public String root() {
        return "redirect:/auth/login";
    }
}
