package com.pos.customer_service.service;

import com.pos.customer_service.dto.CustomerRequest;
import com.pos.customer_service.dto.CustomerResponse;
import com.pos.customer_service.model.Customer;
import com.pos.customer_service.repo.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository repo;

    public CustomerResponse createCustomer(CustomerRequest request) {
        if (repo.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        Customer customer = Customer.builder()
                .name(request.getName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .build();

        repo.save(customer);

        return mapToResponse(customer);
    }

    public List<CustomerResponse> getAllCustomers() {
        return repo.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public CustomerResponse getCustomerById(Long id) {
        return repo.findById(id)
                .map(this::mapToResponse)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    public void deleteCustomer(Long id) {
        repo.deleteById(id);
    }

    private CustomerResponse mapToResponse(Customer c) {
        return new CustomerResponse(c.getId(), c.getName(), c.getEmail(), c.getPhone());
    }
}
