package com.ecomm.backend.Models.dto;

import java.time.LocalDate;
import java.util.List;

public record OrderSummaryDTO(
    String orderId,
    String customerName,
    String email,
    String status,
    LocalDate orderDate,
    int itemCount,
    List<OrderItemResponse> items
) {
}