package com.microservice.QuizService.Repositories;


import com.microservice.QuizService.Models.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepo extends JpaRepository<Quiz,Integer> {
}
