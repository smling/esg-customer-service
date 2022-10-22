package com.esg.services.customer.controllers;

import com.esg.services.customer.models.Customer;
import com.esg.services.customer.services.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("customer")
public class CustomerController {
    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<Mono<Customer>> create(
            @RequestBody Customer customer
    ) {
        Mono<Customer> customerMono = customerService.createCustomer(customer);
            return ResponseEntity.ok().body(customerMono);
    }
    @GetMapping
    public ResponseEntity<Mono<Customer>> get(
            @RequestParam String ref
    ) {
        return ResponseEntity.ok().body(customerService.getCustomerByRef(ref));
    }
}
