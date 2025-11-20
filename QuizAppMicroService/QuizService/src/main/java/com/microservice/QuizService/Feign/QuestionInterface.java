package com.microservice.QuizService.Feign;

import com.microservice.QuizService.DTO.CalculateScore;
import com.microservice.QuizService.DTO.QuestionResponse;
import com.microservice.QuizService.DTO.ResultResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("question-service")
public interface QuestionInterface {
    @GetMapping("/questions/generateQuestion")
    public ResponseEntity<List<Integer>> getQuestionsByCategory(@RequestParam String category, @RequestParam Integer limit);

    @GetMapping("/questions/getQuestions")
    public ResponseEntity<List<QuestionResponse>> getQuestions(@RequestParam List<Integer> questionIds);

    @PostMapping("/questions/submitAndCalculate")
    public ResponseEntity<ResultResponse> submitAndCalculate(@RequestBody List<CalculateScore> calculateScores);
}
