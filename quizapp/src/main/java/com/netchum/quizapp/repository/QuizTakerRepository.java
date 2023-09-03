package com.netchum.quizapp.repository;

import com.netchum.quizapp.entity.QuizTaker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizTakerRepository extends JpaRepository<QuizTaker, Integer> {

}
