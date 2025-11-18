package com.microservice.question_service.Controllers;


import com.microservice.question_service.DTO.AddQuestion;
import com.microservice.question_service.DTO.CalculateScore;
import com.microservice.question_service.DTO.QuestionResponse;
import com.microservice.question_service.DTO.ResultResponse;
import com.microservice.question_service.Models.Category;
import com.microservice.question_service.Models.Questions;
import com.microservice.question_service.Services.QuestionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/questions")
public class QuestionController {
    @Autowired
    QuestionServices questionServices;

    @GetMapping("/getAll")
    public ResponseEntity<List<Questions>> getAllQuestions() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(questionServices.getAllQuestions());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/{category}")
    public ResponseEntity<List<Questions>> getCategoryBasedQuestions(@PathVariable Category category) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(questionServices.getByCategory(category.name()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/addQuestion")
    public ResponseEntity<String> addQuestion(@RequestBody AddQuestion question) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(questionServices.addQuestion(question));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/generateQuestion")
    public ResponseEntity<List<Integer>> getQuestionsByCategory(String category, Integer limit) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(questionServices.getQuestionsByCategory(category, limit));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/getQuestions")
    public ResponseEntity<List<QuestionResponse>> getQuestions(@RequestParam int[] questionIds) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(questionServices.getQuestions(questionIds));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/submitAndCalculate")
    public ResponseEntity<ResultResponse> submitAndCalculate(@RequestBody List<CalculateScore> calculateScores) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(questionServices.sumitAndCalculate(calculateScores));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

