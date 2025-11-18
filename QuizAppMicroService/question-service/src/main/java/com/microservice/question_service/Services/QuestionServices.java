package com.microservice.question_service.Services;


import com.microservice.question_service.DTO.AddQuestion;
import com.microservice.question_service.DTO.CalculateScore;
import com.microservice.question_service.DTO.QuestionResponse;
import com.microservice.question_service.DTO.ResultResponse;
import com.microservice.question_service.Models.Questions;
import com.microservice.question_service.Repositories.QuestionRepo;
import com.microservice.question_service.util.QuestionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionServices {
    @Autowired
    QuestionRepo questionRepo;
    @Autowired
    QuestionMapper questionMapper;

    public List<Questions> getAllQuestions() {
        return questionRepo.findAll();
    }

    public List<Questions> getByCategory(String category) {
        Questions prob = new Questions();
        prob.setCategory(category);
        Example<Questions> example = Example.of(prob);
        return questionRepo.findBy(example, FluentQuery.FetchableFluentQuery::all);
    }

    public String addQuestion(AddQuestion question) {
        questionRepo.save(questionMapper.toEntity(question));
        return "Successfully Added";
    }

    public List<Integer> getQuestionsByCategory(String category, Integer limit) {
        return questionRepo.findRandomQuestionsByCategory(category, limit);
    }

    public List<QuestionResponse> getQuestions(int[] questionIds) {
        List<Questions> questions = new ArrayList<>();
        for (int id : questionIds)
            questions.add(questionRepo.findById(id).get());
        return questionMapper.quizListToQuestionResponseList(questions);
    }

    public ResultResponse sumitAndCalculate(List<CalculateScore> calculateScores) {
        int score = 0;
        for (CalculateScore calculateScore : calculateScores) {
            Questions questions = questionRepo.findById(calculateScore.id()).get();
            if (questions.getRightAnswer().equals(calculateScore.answer()))
                score++;
        }
        return new ResultResponse(score, "Thanks for participating");
    }
}
