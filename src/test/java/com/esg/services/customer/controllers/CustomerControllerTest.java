package com.esg.services.customer.controllers;

import com.esg.services.customer.configurations.HttpErrorResponseAdvise;
import com.esg.services.customer.fixtures.CustomerFixture;
import com.esg.services.customer.models.Customer;
import com.esg.services.customer.services.CustomerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

@SpringBootTest
class CustomerControllerTest {
    @InjectMocks
    CustomerController customerController;

    @Mock
    CustomerService customerService;

    @Mock
    HttpErrorResponseAdvise httpErrorResponseAdvise;

    @Test
    @DisplayName("Given valid data, when call method create(), then success")
    void givenValidData_whenCallMethod_create_thenSuccess() {
        Customer customer = CustomerFixture.mockNormalCustomerData();
        Mockito.when(customerService.createCustomer(customer)).thenReturn(customer);
        Assertions.assertDoesNotThrow(()-> {
            ResponseEntity<Customer> response = customerController.create("1234", customer);
            Assertions.assertTrue(response.getStatusCode().is2xxSuccessful());
        });
    }

    @Test
    @DisplayName("Given valid data, when call method get(), then success")
    void givenValidData_whenCallMethod_get_thenSuccess() {
        Customer customer = CustomerFixture.mockNormalCustomerData();
        Mockito.when(customerService.getCustomerByRef(customer.getCustomerRef())).thenReturn(customer);
        Assertions.assertDoesNotThrow(()-> {
            ResponseEntity<Customer> response = customerController.get("1234", customer.getCustomerRef());
            Assertions.assertTrue(response.getStatusCode().is2xxSuccessful());
            Assertions.assertEquals(customer, response.getBody());
        });
    }
}