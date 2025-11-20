package com.microservice.QuizService.util;


import com.microservice.QuizService.DTO.QuestionResponse;
import com.microservice.QuizService.Models.Questions;
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
    List<QuestionResponse> quizListToQuestionResponseList(List<QuestionResponse> quizzes);

}

