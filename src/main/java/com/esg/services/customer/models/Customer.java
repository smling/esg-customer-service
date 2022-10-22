package com.esg.services.customer.models;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="Customer")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {
    @Id
    @Column(length = 255)
    private String customerRef;
    @Column(length = 1000)
    private String customerName;
    @Column(length = 200)
    private String addressLine1;
    @Column(length = 200)
    private String addressLine2;
    @Column(length = 200)
    private String town;
    @Column(length = 200)
    private String county;
    @Column(length = 200)
    private String country;
    @Column(length = 6)
    private String postCode;
}
