package com.microservice.QuizService.Services;


import com.microservice.QuizService.DTO.CalculateScore;
import com.microservice.QuizService.DTO.QuestionResponse;
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
    QuestionMapper questionMapper;
    public String createQuiz(String title, String category, int limit) {

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
//        quiz.setQuestions(questionRepo.findRandomQuestionsByCategory(category, limit));
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
        return questionMapper.quizListToQuestionResponseList(quiz.getQuestions());
    }

    public Integer submitQuiz(List<CalculateScore> calculateScores) {
        int score=0;
        for (CalculateScore calculateScore:calculateScores){
//            Questions questions=questionRepo.findById(calculateScore.id()).orElse(null);
//            if (questions!=null&& Objects.equals(questions.getRightAnswer(), calculateScore.answer()))
//                score++;
        }
        return score;
    }
}
