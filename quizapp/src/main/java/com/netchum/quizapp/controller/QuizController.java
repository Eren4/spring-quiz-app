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
import java.util.Optional;

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
        if(username.isEmpty()) {
            model.addAttribute("usernameEmptyError", "Please don't leave your username empty");
            return "home-page";
        }
        else if(quizTakerService.quizTakerByUsernameExists(username)) {
            model.addAttribute("usernameError", "User already exists.");
            return "home-page";
        }

        session.setAttribute("username", username);
        session.setAttribute("currentQuestionId", 1);

        QuizTaker quizTaker = new QuizTaker(username, 0, LocalDate.now());

        quizTakerService.updateQuizTaker(quizTaker);

        return "redirect:/question";
    }

    @GetMapping("/question")
    public String showQuestion(HttpSession session, Model model) {
        // Retrieve the current username and progress from the session or database
        String username = (String) session.getAttribute("username");
        Integer currentQuestionId = (Integer) session.getAttribute("currentQuestionId");

        Optional<Question> question = questionService.getQuestionById(currentQuestionId);

        // Pass question data to the template
        model.addAttribute("username", username);
        model.addAttribute("question", question.get());
        model.addAttribute("currentQuestionId", currentQuestionId);

        return "question-page"; // Display the question page
    }

    @PostMapping("/question")
    public String answerQuestion(@RequestParam("selectedOption") int selectedOption,
                                 @RequestParam("username") String username,
                                 HttpSession session,
                                 Model model) {

        // Retrieve the current progress (current question number) from the session or database
        Integer currentQuestionId = (Integer) session.getAttribute("currentQuestionId");

        // Check if it's the final question (you need to know the total number of questions)
        int totalQuestions = questionService.getAllQuestions().size(); // Implement this method

        int scoreValue = (int) (100 / totalQuestions);

        Question question = questionService.getQuestionById(currentQuestionId).get();

        QuizTaker quizTaker = quizTakerService.getQuizTakerByUsername(username);

        if(question.getCorrectOptionIndex() == selectedOption) {
            quizTaker.increaseScore(scoreValue);
            quizTakerService.updateQuizTaker(quizTaker);
        }

        if (currentQuestionId < totalQuestions) {
            // If not the final question, increment the current question number
            currentQuestionId++;
            session.setAttribute("currentQuestionId", currentQuestionId);
            return "redirect:/question"; // Redirect to the next question
        } else {
            // If it's the final question, redirect to the finish page
            model.addAttribute("username", quizTaker.getUsername());
            model.addAttribute("score", quizTaker.getScore());
            model.addAttribute("date", quizTaker.getDateRegistered());
            return "finish-page";
        }
    }

    @GetMapping("/leaderboard")
    public String showLeaderboard() {
        return "leaderboard-page";
    }
}
