package com.microservices.QuizzApp.util;

import com.microservices.QuizzApp.DTO.AddQuestion;
import com.microservices.QuizzApp.DTO.QuestionResponse;
import com.microservices.QuizzApp.Models.Questions;
import com.microservices.QuizzApp.Models.Quiz;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface QuestionMapper {
    @Mapping(target = "id", source = "id")
    @Mapping(target = "questionTitle", source = "questionTitle")
    @Mapping(target = "option1", source = "option1")
    @Mapping(target = "option2", source = "option2")
    @Mapping(target = "option3", source = "option3")
    @Mapping(target = "option4", source = "option4")
    QuestionResponse quizToQuestionResponse(Questions quiz);
    List<QuestionResponse> quizListToQuestionResponseList(List<Questions> quizzes);

    Questions toEntity(AddQuestion dto);
    AddQuestion toDto(Questions entity);
}

