package com.microservices.QuizzApp.Repositories;

import com.microservices.QuizzApp.Models.Questions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepo extends JpaRepository<Questions,Integer> {
    @Query(value = "SELECT * FROM questions WHERE category = :category ORDER BY RANDOM() LIMIT :limit",nativeQuery = true)
    List<Questions> findRandomQuestionsByCategory(String category,int limit);

}
