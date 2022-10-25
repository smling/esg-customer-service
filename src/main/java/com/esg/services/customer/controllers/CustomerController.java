package com.esg.services.customer.controllers;

import com.esg.services.customer.Constants;
import com.esg.services.customer.models.Customer;
import com.esg.services.customer.models.dto.ErrorResponse;
import com.esg.services.customer.services.CustomerService;
import io.swagger.annotations.ApiOperation;

import io.swagger.annotations.Contact;
import io.swagger.annotations.Example;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import net.bytebuddy.implementation.Implementation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.util.Objects;

@RestController
@RequestMapping("api/customer")
public class CustomerController {
    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    @ApiOperation(value = "Create customer.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Customer record created", content = @Content( schema = @Schema(implementation = Customer.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content( schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content( schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content( schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<Customer> create(
            @RequestHeader(name = Constants.API_KEY_HEADER_NAME) String apiKey,
            @RequestBody Customer customer
    ) {
        Customer customerMono = customerService.createCustomer(customer);
            return ResponseEntity.status(HttpStatus.CREATED).body(customerMono);
    }
    @GetMapping
    @ApiOperation(value = "Find customer by customerRef.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found customer record", content = @Content( schema = @Schema(implementation = Customer.class))),
            @ApiResponse(responseCode = "204", description = "Record not found"),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content( schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content( schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content( schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<Customer> get(
            @RequestHeader(name = Constants.API_KEY_HEADER_NAME) String apiKey,
            @RequestParam String ref
    ) {
        Customer customer = customerService.getCustomerByRef(ref);
        if(Objects.nonNull(customer)) {
            return ResponseEntity.ok().body(customer);
        }
        return  ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
