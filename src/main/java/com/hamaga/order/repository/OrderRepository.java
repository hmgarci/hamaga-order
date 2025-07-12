package com.hamaga.order.repository;

import com.hamaga.order.model.Order;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface OrderRepository extends ReactiveCrudRepository<Order, Long> {
    // Additional query methods can be defined here if needed
}
