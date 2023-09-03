package com.netchum.quizapp.service;

import com.netchum.quizapp.repository.QuizTakerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuizTakerService {

    private final QuizTakerRepository quizTakerRepository;

    @Autowired
    public QuizTakerService(QuizTakerRepository quizTakerRepository) {
        this.quizTakerRepository = quizTakerRepository;
    }
}
