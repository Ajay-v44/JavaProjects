package com.ecomm.backend.Mappers;

import com.ecomm.backend.Models.Order;
import com.ecomm.backend.Models.OrderItem;
import com.ecomm.backend.Models.dto.OrderItemResponse;
import com.ecomm.backend.Models.dto.OrderResponse;
import com.ecomm.backend.Models.dto.OrderSummaryDTO;
import org.mapstruct.*;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderMapper {

    @Mapping(target = "items", source = "orderItems")
    OrderResponse orderToOrderResponse(Order order);

    List<OrderResponse> ordersToOrderResponses(List<Order> orders);
    
    @Mapping(target = "productName", source = "product.name")
    OrderItemResponse orderItemToOrderItemResponse(OrderItem orderItem);
    
    default OrderSummaryDTO orderToOrderSummaryDTO(Order order) {
        if (order == null) {
            return null;
        }
        
        List<OrderItemResponse> itemResponses = order.getOrderItems().stream()
            .map(this::orderItemToOrderItemResponse)
            .collect(Collectors.toList());
            
        return new OrderSummaryDTO(
            order.getOrderId(),
            order.getCustomerName(),
            order.getEmail(),
            order.getStatus(),
            order.getOrderDate(),
            order.getOrderItems().size(),
            itemResponses
        );
    }
    
    List<OrderSummaryDTO> ordersToOrderSummaryDTOs(List<Order> orders);
}