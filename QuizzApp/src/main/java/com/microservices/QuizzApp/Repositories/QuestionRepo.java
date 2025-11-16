package com.microservices.QuizzApp.Repositories;

import com.microservices.QuizzApp.Models.Questions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepo extends JpaRepository<Questions,Integer> {

}
