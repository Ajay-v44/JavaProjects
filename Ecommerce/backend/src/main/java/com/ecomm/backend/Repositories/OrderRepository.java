package com.ecomm.backend.Repositories;

import com.ecomm.backend.Models.Order;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {
    @EntityGraph(attributePaths = {"orderItems", "orderItems.product"})
    @Query("SELECT o FROM orders o")
    List<Order> findAllWithItemsAndProducts();
}
