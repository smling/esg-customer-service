package com.esg.services.customer.services;

import com.esg.services.customer.exceptions.NotFoundException;
import com.esg.services.customer.fixtures.CustomerFixture;
import com.esg.services.customer.models.Customer;
import com.esg.services.customer.repositories.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.stream.Stream;

@SpringBootTest
class CustomerServiceTest {
    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    @Test
    @DisplayName("Given valid data, when call method createCustomer(), then success")
    void givenValidData_whenCallMethod_createCustomer_thenSuccess() {
        Customer customer = CustomerFixture.mockNormalCustomerData();
        Mockito.when(customerRepository.save(customer)).thenReturn(customer);
        Assertions.assertDoesNotThrow(()-> {
            Customer savedCustomer = customerService.createCustomer(customer);
            Assertions.assertEquals(customer, savedCustomer);
        });
    }

    static Stream<Arguments> mockInvalidCustomerTestCasesParameters() {
        Customer hitMaxLengthCustomerData = CustomerFixture.mockNormalCustomerData();
        hitMaxLengthCustomerData.setPostCode("INVALID_POST_CODE");
        return Stream.of(
                Arguments.of("Data hit max length", hitMaxLengthCustomerData, new RuntimeException())
        );
    }

    @ParameterizedTest(name="{0}")
    @DisplayName("Given invalid data, when call method createCustomer(), then exception thrown")
    @MethodSource("mockInvalidCustomerTestCasesParameters")
    void givenInvalidData_whenCallMethod_createCustomer_thenExceptionThrown(String description, Customer customer, Exception exception) {
        Mockito.when(customerRepository.save(customer)).thenThrow(new RuntimeException());
        Assertions.assertThrows(exception.getClass(), ()-> {
            Customer savedCustomer =  customerService.createCustomer(customer);
            Assertions.assertEquals(customer, savedCustomer);
        });
    }

    static Stream<Arguments> mockValidGetCustomerRefTestCasesParameters() {
        Customer customer = CustomerFixture.mockNormalCustomerData();
        return Stream.of(
                Arguments.of("CustomerRef found in database", customer.getCustomerRef(), Optional.of(customer))
        );
    }

    @ParameterizedTest(name="{0}")
    @DisplayName("Given valid data, when call method getCustomerRef(), then success")
    @MethodSource("mockValidGetCustomerRefTestCasesParameters")
    void givenValidData_whenCallMethod_getCustomerRef_thenSuccess(String description, String customerRef, Optional<Customer> customer) {
        Mockito.when(customerRepository.findById(customerRef)).thenReturn(customer);
        Assertions.assertDoesNotThrow(()-> {
            Customer searchedCustomer = customerService.getCustomerByRef(customerRef);
            Assertions.assertTrue(customer.isPresent());
            Assertions.assertEquals(customer.get(), searchedCustomer);
        });
    }



    static Stream<Arguments> mockInvalidGetCustomerRefTestCasesParameters() {
        Customer customer = CustomerFixture.mockNormalCustomerData();
        return Stream.of(
                Arguments.of("CustomerRef not found in database", customer.getCustomerRef(), Optional.empty(), new NotFoundException("Mock exception"))
        );
    }

    @ParameterizedTest(name="{0}")
    @DisplayName("Given invalid data, when call method getCustomerRef(), then exception thrown")
    @MethodSource("mockInvalidGetCustomerRefTestCasesParameters")
    void givenInvalidData_whenCallMethod_getCustomerRef_thenSuccess(String description, String customerRef, Optional<Customer> customer, Exception exception) {
        Mockito.when(customerRepository.findById(customerRef)).thenReturn(customer);
        Assertions.assertThrows(exception.getClass(), ()-> {
            customerService.getCustomerByRef(customerRef);
        });
    }
}