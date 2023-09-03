package com.netchum.quizapp.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "quiz_taker")
public class QuizTaker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "username")
    private String username;

    @Column(name = "score")
    private int score;

    @Column(name = "date_registered")
    private LocalDate dateRegistered;

    public QuizTaker() {

    }

    public QuizTaker(String username, int score, LocalDate dateRegistered) {
        this.username = username;
        this.score = score;
        this.dateRegistered = dateRegistered;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public LocalDate getDateRegistered() {
        return dateRegistered;
    }

    public void setDateRegistered(LocalDate dateRegistered) {
        this.dateRegistered = dateRegistered;
    }

    public void increaseScore(int value) {
        this.score += value;
    }

    @Override
    public String toString() {
        return "QuizTaker{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", score=" + score +
                ", dateRegistered=" + dateRegistered +
                '}';
    }
}
