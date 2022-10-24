package com.esg.services.customer.services;

import com.esg.services.customer.exceptions.NotFoundException;
import com.esg.services.customer.exceptions.RecordAlreadyExistsException;
import com.esg.services.customer.models.Customer;
import com.esg.services.customer.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {
    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer createCustomer(Customer customer) {
        if(customerRepository.existsById(customer.getCustomerRef())) {
            throw new RecordAlreadyExistsException();
        }
        return customerRepository.save(customer);
    }

    public Customer getCustomerByRef(String customerRef) {
        Optional<Customer> customerOptional = customerRepository.findById(customerRef);
        return customerOptional.orElseThrow(()->new NotFoundException("No record found for "+customerRef+"."));
    }
}
