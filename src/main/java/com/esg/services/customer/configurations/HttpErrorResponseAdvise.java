package com.esg.services.customer.configurations;

import com.esg.services.customer.exceptions.InvalidRequestException;
import com.esg.services.customer.exceptions.NotFoundException;
import com.esg.services.customer.exceptions.RecordAlreadyExistsException;
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

    @ExceptionHandler(value = RecordAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleRecordAlreadyExistsException(
            RecordAlreadyExistsException ex)
    {
        return ErrorResponse.builder().code(HttpStatus.BAD_REQUEST.value()+"").message("Record already exists").build();
    }



    @ExceptionHandler(value = InvalidRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleInvalidRequestException(
            InvalidRequestException ex)
    {
        return ErrorResponse.builder().code(HttpStatus.BAD_REQUEST.value()+"").message(ex.getMessage()).build();
    }
}
