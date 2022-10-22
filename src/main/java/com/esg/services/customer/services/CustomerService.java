package com.esg.services.customer.services;

import com.esg.services.customer.exceptions.NotFoundException;
import com.esg.services.customer.models.Customer;
import com.esg.services.customer.repositories.CustomerRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.NotActiveException;
import java.util.Optional;

@Service
public class CustomerService {
    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Mono<Customer> createCustomer(Customer customer) {
        return Mono.create(callback-> {
            Customer savedCustomer = customerRepository.save(customer);
            callback.success(savedCustomer);
        });
    }

    public Mono<Customer> getCustomerByRef(String customerRef) {
        Optional<Customer> customerOptional = customerRepository.findById(customerRef);
        return customerOptional.map(Mono::just).orElseThrow(()->new NotFoundException("No record found for "+customerRef+"."));
    }
}
