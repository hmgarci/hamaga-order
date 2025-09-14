package com.hamaga.order.repository;

import com.hamaga.order.model.Product;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ProductRepository extends ReactiveCrudRepository<Product, Long> {
    // Additional query methods can be defined here if needed
}
