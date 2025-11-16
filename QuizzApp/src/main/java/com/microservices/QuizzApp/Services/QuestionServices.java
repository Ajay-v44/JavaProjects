package com.microservices.QuizzApp.Services;

import com.microservices.QuizzApp.Models.Questions;
import com.microservices.QuizzApp.Repositories.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServices {
    @Autowired
    QuestionRepo questionRepo;

    public List<Questions> getAllQuestions() {
        return questionRepo.findAll();
    }

    public List<Questions> getByCategory(String category) {
        Questions prob=new Questions();
        prob.setCategory(category);
        Example<Questions> example=Example.of(prob);
        return questionRepo.findBy(example, FluentQuery.FetchableFluentQuery::all);
    }
}
