package com.pos.order_service.controller;

import com.pos.order_service.model.Order;
import com.pos.order_service.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        return orderService.createOrder(order);
    }

    @PutMapping("/{id}/status/{status}")
    public Order updateOrderStatus(@PathVariable Long id, @PathVariable String status) {
        return orderService.updateStatus(id, status);
    }
}
