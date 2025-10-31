package com.ecomm.backend.Models.dto;

import java.util.List;

public record OrderRequest(
        String customerName,
        String email,
        List<OrderItemRequest> orderItemRequests
) {
}
