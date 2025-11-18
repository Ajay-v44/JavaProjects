package com.microservices.QuizzApp.Controllers;

import com.microservices.QuizzApp.DTO.CalculateScore;
import com.microservices.QuizzApp.DTO.QuestionResponse;
import com.microservices.QuizzApp.Models.Quiz;
import com.microservices.QuizzApp.Services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @PostMapping("/v1/createQuiz")
    public ResponseEntity<String> createQuiz(@RequestParam String title, @RequestParam String category, @RequestParam int limit){
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(quizService.createQuiz(title,category,limit));
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
    @GetMapping("/v1/getQuiz/{id}")
    public ResponseEntity<Quiz> getQuizById(@PathVariable int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(quizService.getQuestionById(id));
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/v2/getQuiz/{id}")
    public ResponseEntity<List<QuestionResponse>> getQuizByIdV2(@PathVariable int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(quizService.getQuestionByIdV2(id));
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
    @PostMapping("/v1/submitQuiz")
    public ResponseEntity<Integer> submitQuiz(@RequestBody List<CalculateScore> calculateScores){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(quizService.submitQuiz(calculateScores));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
