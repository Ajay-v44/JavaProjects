package com.microservice.question_service.DTO;

public record ResultResponse(
        int score,
        String message
) {
}
