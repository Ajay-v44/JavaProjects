package com.ecomm.backend.Models.dto;

import java.time.LocalDate;

public record OrderSummaryDTO(
    String orderId,
    String customerName,
    String email,
    String status,
    LocalDate orderDate,
    int itemCount
) {
}