package com.pos.order_service.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String orderNumber;
    private Long customerId;
    private Double totalAmount;
    private String status; // e.g., PENDING, COMPLETED
}

