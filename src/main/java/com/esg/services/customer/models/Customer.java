package com.esg.services.customer.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Size;
@Entity(name="Customer")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {
    @Id
    @Size(min =1, max = 255)
    private String customerRef;
    @Size(min =0, max = 1000)
    private String customerName;
    @Size(min =0, max = 200)
    private String addressLine1;
    @Size(min =0, max = 200)
    private String addressLine2;
    @Size(min =0, max = 200)
    private String town;
    @Size(min =0, max = 200)
    private String county;
    @Size(min =0, max = 200)
    private String country;
    @Size(min =6, max = 6)
    private String postCode;
}
