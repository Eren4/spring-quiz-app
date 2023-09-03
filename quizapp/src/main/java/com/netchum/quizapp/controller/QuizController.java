package com.netchum.quizapp.controller;

import com.netchum.quizapp.entity.Question;
import com.netchum.quizapp.entity.QuizTaker;
import com.netchum.quizapp.service.QuestionService;
import com.netchum.quizapp.service.QuizTakerService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
public class QuizController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuizTakerService quizTakerService;

    @GetMapping("/")
    public String homePage() {
        return "home-page";
    }

    @PostMapping("/start")
    public String startQuiz(@RequestParam("username") String username,
                            HttpSession session,
                            Model model) {
        if(quizTakerService.quizTakerByUsernameExists(username)) {
            model.addAttribute("usernameError", "User already exists.");
            return "home-page";
        }

//        QuizTaker quizTaker = new QuizTaker(username, 0, LocalDate.now());
//
//        quizTakerService.updateQuizTaker(quizTaker);

        model.addAttribute("username", username);

        return "question-page";
    }
}
