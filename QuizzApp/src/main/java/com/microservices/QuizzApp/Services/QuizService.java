package com.microservices.QuizzApp.Services;

import com.microservices.QuizzApp.DTO.CalculateScore;
import com.microservices.QuizzApp.DTO.QuestionResponse;
import com.microservices.QuizzApp.Models.Questions;
import com.microservices.QuizzApp.Models.Quiz;
import com.microservices.QuizzApp.Repositories.QuestionRepo;
import com.microservices.QuizzApp.Repositories.QuizRepo;
import com.microservices.QuizzApp.util.QuestionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class QuizService {

    @Autowired
    private QuizRepo quizRepo;

    @Autowired
    private QuestionRepo questionRepo;
    @Autowired
    QuestionMapper questionMapper;
    public String createQuiz(String title, String category, int limit) {

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questionRepo.findRandomQuestionsByCategory(category, limit));
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
            Questions questions=questionRepo.findById(calculateScore.id()).orElse(null);
            if (questions!=null&& Objects.equals(questions.getRightAnswer(), calculateScore.answer()))
                score++;
        }
        return score;
    }
}
