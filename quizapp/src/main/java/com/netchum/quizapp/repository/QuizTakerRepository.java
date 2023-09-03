package com.netchum.quizapp.repository;

import com.netchum.quizapp.entity.QuizTaker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizTakerRepository extends JpaRepository<QuizTaker, Integer> {

    QuizTaker findByUsername(String username);

    @Query("SELECT CASE WHEN COUNT(qt) > 0 THEN true ELSE false END FROM QuizTaker qt WHERE LOWER(qt.username) = LOWER(:username)")
    boolean quizTakerByUsernameExistsIgnoreCase(@Param("username") String username);
}
