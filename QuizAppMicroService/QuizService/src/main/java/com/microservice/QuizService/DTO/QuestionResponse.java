package com.microservice.QuizService.DTO;

public record QuestionResponse(
        int id,
        String questionTitle,
        String option1,
        String option2,
        String option3,
        String option4
) {
}
