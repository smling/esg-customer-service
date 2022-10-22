package com.esg.services.customer.fixtures;

import com.esg.services.customer.models.Customer;

public class CustomerFixture {
    public static Customer mockNormalCustomerData() {
        return Customer.builder()
                .customerRef("Customer_Ref")
                .customerName("John Doe")
                .addressLine1("test address line 1.")
                .addressLine1("test address line 2.")
                .town("Manchester")
                .county("North West")
                .country("United Kingdom")
                .postCode("M1AAA")
                .build();
    }
}
