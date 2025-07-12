package com.hamaga.order.controller;

import com.hamaga.order.exceptions.SaveOrderException;
import com.hamaga.order.model.Order;
import com.hamaga.order.service.OrderService;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    @PostMapping
    public Mono<Order> createOrder(ServerHttpRequest httpRequest,
                                   @RequestBody Order order){
        return orderService.createOrder(order)
                .onErrorResume(ex ->
                        Mono.error(new SaveOrderException(
                                "Failed to create order: ".concat(ex.getMessage())
                        ))
                );
    }
}
