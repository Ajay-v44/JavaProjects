package com.microservice.question_service.Repositories;

import com.microservice.question_service.Models.Questions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepo extends JpaRepository<Questions,Integer> {
    @Query(value = "SELECT id FROM questions WHERE category = :category ORDER BY RANDOM() LIMIT :limit",nativeQuery = true)
    List<Integer> findRandomQuestionsByCategory(String category, int limit);

}
