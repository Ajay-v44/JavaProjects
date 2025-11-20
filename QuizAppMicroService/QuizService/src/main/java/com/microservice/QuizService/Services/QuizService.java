package com.microservice.QuizService.Services;


import com.microservice.QuizService.DTO.CalculateScore;
import com.microservice.QuizService.DTO.QuestionResponse;
import com.microservice.QuizService.Feign.QuestionInterface;
import com.microservice.QuizService.Models.Quiz;
import com.microservice.QuizService.Repositories.QuizRepo;
import com.microservice.QuizService.util.QuestionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class QuizService {

    @Autowired
    private QuizRepo quizRepo;
    @Autowired
    private QuestionInterface questionInterface;
    @Autowired
    QuestionMapper questionMapper;
    public String createQuiz(String title, String category, int limit) {

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        List<Integer> questionIds=questionInterface.getQuestionsByCategory(category,limit).getBody();
        quiz.setQuestionIds(questionIds);
        quizRepo.save(quiz);
        return "Successfully Added The Questions";
    }

    public Quiz getQuestionById(int id) {
        return quizRepo.findById(id).orElse(null);
    }

    public List<QuestionResponse> getQuestionByIdV2(int id) {
        Quiz quiz=quizRepo.findById(id).orElse(null);
        if (quiz==null)
            return null;
        return questionInterface.getQuestions(quiz.getQuestionIds()).getBody();
    }

    public Integer submitQuiz(List<CalculateScore> calculateScores) {
        return questionInterface.submitAndCalculate(calculateScores).getBody().score();
    }
}
