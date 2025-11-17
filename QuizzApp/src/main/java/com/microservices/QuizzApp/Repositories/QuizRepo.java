package com.microservices.QuizzApp.Repositories;

import com.microservices.QuizzApp.Models.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepo extends JpaRepository<Quiz,Integer> {
}
