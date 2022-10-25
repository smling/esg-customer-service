package com.esg.services.customer.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel("Customer")
public class Customer {
    @Id
    @Size(min =1, max = 255)
    @ApiModelProperty(required = true, position = 1, notes = "Customer unique reference number.", example = "CUST_0001")
    private String customerRef;
    @Size(min =0, max = 1000)
    @ApiModelProperty(required = true, position = 2, notes = "Customer name.", example = "Doctor Who")
    private String customerName;
    @Size(min =0, max = 200)
    @ApiModelProperty(required = true, position = 3, notes = "Address line 1.")
    private String addressLine1;
    @Size(min =0, max = 200)
    @ApiModelProperty(position = 4, notes = "Address line 2.")
    private String addressLine2;
    @Size(min =0, max = 200)
    @ApiModelProperty(required = true, position = 5, notes = "Town.")
    private String town;
    @Size(min =0, max = 200)
    @ApiModelProperty(required = true, position = 6, notes = "County.")
    private String county;
    @Size(min =0, max = 200)
    @ApiModelProperty(required = true, position = 7, notes = "Country.")
    private String country;
    @ApiModelProperty(required = true, position = 8, notes = "Post code.")
    @Size(min =6, max = 6)
    private String postCode;
}
