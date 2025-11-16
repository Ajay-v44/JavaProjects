package com.microservices.QuizzApp.Controllers;

import com.microservices.QuizzApp.Models.Category;
import com.microservices.QuizzApp.Models.Questions;
import com.microservices.QuizzApp.Services.QuestionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/questions")
public class QuestionController {
    @Autowired
    QuestionServices questionServices;

    @GetMapping("/getAll")
    public ResponseEntity<List<Questions>> getAllQuestions(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(questionServices.getAllQuestions());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/{category}")
    public ResponseEntity<List<Questions>> getCategoryBasedQuestions(@PathVariable Category category){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(questionServices.getByCategory(category.name()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
