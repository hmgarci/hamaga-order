package com.hamaga.order.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table("orders")
public class Order {
    @Id
    private Long id;
    private String customerId;
    private String product;
    private Integer quantity;
    private String status;
}
