package com.netchum.quizapp.service;

import com.netchum.quizapp.entity.QuizTaker;
import com.netchum.quizapp.repository.QuizTakerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class QuizTakerService {

    private final QuizTakerRepository quizTakerRepository;

    @Autowired
    public QuizTakerService(QuizTakerRepository quizTakerRepository) {
        this.quizTakerRepository = quizTakerRepository;
    }

    public boolean quizTakerByUsernameExists(String username) {
        return quizTakerRepository.quizTakerByUsernameExistsIgnoreCase(username);
    }

    public QuizTaker getQuizTakerByUsername(String username) {
        return quizTakerRepository.findByUsername(username);
    }

    public void updateQuizTaker(QuizTaker quizTaker) {
        quizTakerRepository.save(quizTaker);
    }
}
