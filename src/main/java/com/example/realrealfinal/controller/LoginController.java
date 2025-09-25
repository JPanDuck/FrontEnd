package com.example.realrealfinal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        // /WEB-INF/views/login.jsp 로 이동
        return "login";
    }

    // 루트(/)로 접근하면 로그인 페이지로 바로 리다이렉트 하고 싶을 때
    @GetMapping("/")
    public String root() {
        return "redirect:/login";
    }
}
