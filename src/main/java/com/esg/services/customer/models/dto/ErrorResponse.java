package com.esg.services.customer.models.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.lang.annotation.Documented;

@Data
@Builder
@ApiModel("Error response")
public class ErrorResponse {
    @ApiModelProperty(required = true, notes = "Error code. Normally it align with HTTP status code.", example = "401")
    private String code;
    @ApiModelProperty(required = true, notes = "Message", example = "Not authorized")
    private String message;
}
