package com.microservices.QuizzApp.DTO;

public record AddQuestion(
         String questionTitle,
         String option1,
         String option2,
         String option3,
         String option4,
         String rightAnswer,
         String difficultylevel,
         String category
) {
}
