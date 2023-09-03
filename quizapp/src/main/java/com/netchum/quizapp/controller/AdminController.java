package com.netchum.quizapp.controller;

import com.netchum.quizapp.entity.Admin;
import com.netchum.quizapp.service.AdminService;
import com.netchum.quizapp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminController {

    @Autowired
    AdminService adminService;

    @Autowired
    QuestionService questionService;

    @GetMapping("/login")
    public String showAdminLoginPage() {
        return "admin-login-page";
    }

    @PostMapping("/question-list")
    public String showQuestionEditPage(@RequestParam("adminEmail") String adminEmail,
                                       @RequestParam("adminPassword") String adminPassword,
                                       Model model) {
        if(!adminService.authenticateAdmin(adminEmail, adminPassword)) {
            model.addAttribute("adminError", "Incorrect email or password");
            return "admin-login-page";
        }

        model.addAttribute("questions", questionService.getAllQuestions());

        return "admin-question-list-page";
    }
}
