package com.hamaga.order.service;

import com.hamaga.order.exceptions.SaveOrderException;
import com.hamaga.order.exceptions.StockNoAvailableException;
import com.hamaga.order.exceptions.messages.ErrorCode;
import com.hamaga.order.model.Order;
import com.hamaga.order.repository.OrderRepository;
import com.hamaga.order.repository.ProductRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class OrderService {

    private static final String PENDING_STATUS = "PENDING";
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderService(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    public Mono<Order> createOrder(Order order){
        order.setStatus(PENDING_STATUS);
            return productRepository.findById(order.getProductId())
                .filter(productExists -> productExists.getAvailableQuantity() > order.getQuantity())
                    .flatMap(product -> this.createOrderIfStockAvailable(order))
                .switchIfEmpty(Mono.defer(() -> Mono.error(new StockNoAvailableException(ErrorCode.STOCK_NOT_AVAILABLE,
                        order.getProductId()))));
    }

    private Mono<Order> createOrderIfStockAvailable(Order order) {
        return orderRepository.save(order)
                .onErrorResume(e -> Mono.error(new SaveOrderException(
                        ErrorCode.ERROR_TO_SAVE_ORDER, e.getMessage())));
    }


}
