package com.netchum.quizapp.controller;

import com.netchum.quizapp.entity.Admin;
import com.netchum.quizapp.entity.Question;
import com.netchum.quizapp.service.AdminService;
import com.netchum.quizapp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

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

    @GetMapping("/create-new-question")
    public String showCreateQuestionPage() {
        return "question-creation-page";
    }

    @PostMapping("/create-question")
    public String createQuestion(@RequestParam("questionText") String questionText,
                                 @RequestParam("option0") String option0,
                                 @RequestParam("option1") String option1,
                                 @RequestParam("option2") String option2,
                                 @RequestParam("option3") String option3,
                                 @RequestParam("correctOptionIndex") int correctOptionIndex,
                                 Model model) {

        Question question = new Question(questionText, option0, option1, option2, option3, correctOptionIndex);

        questionService.updateQuestion(question);

        model.addAttribute("questions", questionService.getAllQuestions());

        return "admin-question-list-page";
    }

    @PostMapping("/edit-question")
    public String editQuestionPage(@RequestParam("questionId") int questionId,
                                   Model model) {
        Optional<Question> questionOpt = questionService.getQuestionById(questionId);

        if(questionOpt.isPresent()) {
            Question question = questionOpt.get();

            model.addAttribute("questionId", question.getId());
            model.addAttribute("questionText", question.getQuestionText());
            model.addAttribute("option0", question.getOption0());
            model.addAttribute("option1", question.getOption1());
            model.addAttribute("option2", question.getOption2());
            model.addAttribute("option3", question.getOption3());
            model.addAttribute("correctOptionIndex", question.getCorrectOptionIndex());
        }

        return "question-editing-page";
    }

    @PostMapping("/update-question")
    public String updateQuestion(@RequestParam("questionId") int questionId,
                                 @RequestParam("questionText") String questionText,
                                 @RequestParam("option0") String option0,
                                 @RequestParam("option1") String option1,
                                 @RequestParam("option2") String option2,
                                 @RequestParam("option3") String option3,
                                 @RequestParam("correctOptionIndex") int correctOptionIndex,
                                 Model model) {

        Question question = new Question(questionText, option0, option1, option2, option3, correctOptionIndex);

        question.setId(questionId);

        questionService.updateQuestion(question);

        model.addAttribute("questions", questionService.getAllQuestions());

        return "admin-question-list-page";
    }

}
