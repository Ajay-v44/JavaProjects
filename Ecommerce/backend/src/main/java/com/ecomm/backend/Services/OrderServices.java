package com.ecomm.backend.Services;

import com.ecomm.backend.Mappers.OrderMapper;
import com.ecomm.backend.Models.Order;
import com.ecomm.backend.Models.OrderItem;
import com.ecomm.backend.Models.Product;
import com.ecomm.backend.Models.dto.OrderItemRequest;
import com.ecomm.backend.Models.dto.OrderItemResponse;
import com.ecomm.backend.Models.dto.OrderRequest;
import com.ecomm.backend.Models.dto.OrderResponse;
import com.ecomm.backend.Models.dto.OrderSummaryDTO;
import com.ecomm.backend.Repositories.OrderRepository;
import com.ecomm.backend.Repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderServices {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    VectorStore vectorStore;

    @Transactional
    public OrderResponse placeOrder(OrderRequest orderRequest) {

        Order order = new Order();
        String orderId = "OD" + UUID.randomUUID().toString().substring(0, 8)
                .toUpperCase();
        order.setOrderId(orderId);
        order.setCustomerName(orderRequest.customerName());
        order.setEmail(orderRequest.email());
        order.setOrderDate(LocalDate.now());
        order.setStatus("PLACED");

        List<OrderItem> orderItems = new ArrayList<>();
        for (OrderItemRequest itemRequest : orderRequest.orderItemRequests()) {
//            check product and quantity reduction
            Product product = productRepository.findById(itemRequest.productId()).orElseThrow(() -> new RuntimeException("Product Not Found"));
            product.setStockQuantity(product.getStockQuantity() - itemRequest.quantity());
            productRepository.save(product);

//            Embeding
            String filter = String.format("productId == %s", String.valueOf(product.getId()));
            vectorStore.delete(filter);

            String updatedContent = String.format("""
                            
                            Product Name: %s
                            Description: %s
                            Brand: %s
                            Category: %s
                            Price: %.2f
                            Release Date: %s
                            Available: %s
                            Stock: %s
                            """,
                    product.getName(),
                    product.getDescription(),
                    product.getBrand(),
                    product.getCategory(),
                    product.getPrice(),
                    product.getReleaseDate(),
                    product.isProductAvailable(),
                    product.getStockQuantity()
            );

            Document UpdatedDoc = new Document(
                    UUID.randomUUID().toString(),
                    updatedContent,
                    Map.of("productId", String.valueOf(product.getId()))
            );

            vectorStore.add(List.of(UpdatedDoc));


//            Order Items
            OrderItem orderItem = OrderItem.builder()
                    .product(product)
                    .quantity(itemRequest.quantity())
                    .totalPrice(product.getPrice().multiply(BigDecimal.valueOf(itemRequest.quantity())))
                    .order(order)
                    .build();
            orderItems.add(orderItem);
        }
        order.setOrderItems(orderItems);
        Order saveOrder = orderRepository.save(order);


        StringBuilder content = new StringBuilder();
        content.append("Order Summary: \n");
        content.append("Order  ID: ").append(saveOrder.getOrderId()).append("\n");
        content.append("Customer: ").append(saveOrder.getCustomerName()).append("\n");
        content.append("Email: ").append(saveOrder.getEmail()).append("\n");
        content.append("Date: ").append(saveOrder.getOrderDate()).append("\n");
        content.append("Status: ").append(saveOrder.getStatus()).append("\n");
        content.append("Products: \n");

        for (OrderItem orderItem : saveOrder.getOrderItems()) {
            content.append("- ").append(orderItem.getProduct().getName())
                    .append(" x ").append(orderItem.getQuantity())
                    .append(" = ").append(orderItem.getTotalPrice()).append("\n");
        }

        Document document = new Document(
                UUID.randomUUID().toString(),
                content.toString(),
                Map.of("orderId", saveOrder.getOrderId())
        );

        vectorStore.add(List.of(document));


        List<OrderItemResponse> orderItemResponse = new ArrayList<>();

        for (OrderItem item : order.getOrderItems()) {
            OrderItemResponse itemResponse = new OrderItemResponse(item.getProduct().getName(), item.getQuantity(), item.getTotalPrice());
            orderItemResponse.add(itemResponse);
        }

        OrderResponse orderResponse = new OrderResponse(saveOrder.getOrderId(), saveOrder.getCustomerName(), saveOrder.getEmail(), saveOrder.getStatus(), saveOrder.getOrderDate(), orderItemResponse);

        return orderResponse;
    }

    public List<OrderResponse> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        List<OrderResponse> orderResponses = new ArrayList<>();

        for (Order order : orders) {

            List<OrderItemResponse> itemResponses = new ArrayList<>();

            for (OrderItem item : order.getOrderItems()) {

                OrderItemResponse orderItemResponse = new OrderItemResponse(
                        item.getProduct().getName(),
                        item.getQuantity(),
                        item.getTotalPrice()
                );
                itemResponses.add(orderItemResponse);
            }

            OrderResponse orderResponse = new OrderResponse(
                    order.getOrderId(),
                    order.getCustomerName(),
                    order.getEmail(),
                    order.getStatus(),
                    order.getOrderDate(),
                    itemResponses
            );
            orderResponses.add(orderResponse);
        }

        return orderResponses;
    }

    public List<OrderResponse> findAllOrders() {
        List<Order> orders = orderRepository.findAllWithItemsAndProducts();
        return orders.stream()
                .map(order -> new OrderResponse(
                        order.getOrderId(),
                        order.getCustomerName(),
                        order.getEmail(),
                        order.getStatus(),
                        order.getOrderDate(),
                        order.getOrderItems().stream()
                                .map(item -> new OrderItemResponse(
                                        item.getProduct().getName(),
                                        item.getQuantity(),
                                        item.getTotalPrice()))
                                .collect(Collectors.toList())
                ))
                .collect(Collectors.toList());
    }

    public List<OrderSummaryDTO> getAllOrderSummaries() {
        List<Order> orders = orderRepository.findAll();
        return orderMapper.ordersToOrderSummaryDTOs(orders);
    }
}



