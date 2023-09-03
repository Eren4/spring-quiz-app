package com.netchum.quizapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("/login")
    public String showAdminLoginPage() {
        return "admin-login-page";
    }
}
