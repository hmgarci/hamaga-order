package com.hamaga.order.service;

import com.hamaga.order.exceptions.SaveOrderException;
import com.hamaga.order.exceptions.StockNoAvailableException;
import com.hamaga.order.model.Order;
import com.hamaga.order.repository.OrderRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class OrderService {

    private static final String PENDING_STATUS = "PENDING";
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Mono<Order> createOrder(Order order){
        order.setStatus(PENDING_STATUS);
        return orderRepository.findById(order.getId())
                .filter(orderExists -> orderExists.getQuantity() > order.getQuantity())
                .flatMap(this::createOrderIfStockAvailable)
                .switchIfEmpty(Mono.defer(() -> Mono.error(new StockNoAvailableException(
                        "Stock not available for product: " + order.getProduct()))));
    }

    private Mono<Order> createOrderIfStockAvailable(Order order) {
        return orderRepository.save(order)
                .onErrorResume(e -> Mono.error(new SaveOrderException(
                        "Failed to create order due to insufficient stock for product: " + order.getProduct())));
    }


}
