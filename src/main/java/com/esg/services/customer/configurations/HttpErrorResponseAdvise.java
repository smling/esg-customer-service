package com.esg.services.customer.configurations;

import com.esg.services.customer.exceptions.NotFoundException;
import com.esg.services.customer.models.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HttpErrorResponseAdvise {
    @ExceptionHandler(value = NotFoundException.class)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ErrorResponse handleNotFoundException(
            NotFoundException ex)
    {
        return ErrorResponse.builder().code(HttpStatus.NO_CONTENT.value()+"").message(ex.getMessage()).build();
    }
}