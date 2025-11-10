package com.pos.customer_service.controller;

import com.pos.customer_service.dto.CustomerRequest;
import com.pos.customer_service.dto.CustomerResponse;
import com.pos.customer_service.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService service;

    @PostMapping
    public CustomerResponse create(@RequestBody CustomerRequest request) {
        return service.createCustomer(request);
    }

    @GetMapping
    public List<CustomerResponse> getAll() {
        return service.getAllCustomers();
    }

    @GetMapping("/{id}")
    public CustomerResponse getById(@PathVariable Long id) {
        return service.getCustomerById(id);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        service.deleteCustomer(id);
        return "Customer deleted successfully";
    }
}
