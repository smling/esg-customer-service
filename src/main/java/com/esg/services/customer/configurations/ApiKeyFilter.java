package com.esg.services.customer.configurations;


import com.esg.services.customer.Constants;
import com.esg.services.customer.exceptions.InvalidRequestException;
import com.esg.services.customer.models.dto.ErrorResponse;
import com.esg.services.customer.services.AuthenticationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Component
public class ApiKeyFilter implements WebFilter {
    private final AuthenticationService authenticationService;


    public ApiKeyFilter(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        ServerHttpRequest serverHttpRequest = exchange.getRequest();
        if (!serverHttpRequest.getURI().getPath().contains("/api/")) {
            return chain.filter(exchange);
        }
        Optional<String> apiKey = getApiKeyByRequest(serverHttpRequest);
        if (apiKey.isPresent()) {
            if (authenticationService.isApiKeyValid(apiKey.get())) {
                return chain.filter(exchange);
            }
        }
        ServerHttpResponse serverHttpResponse = exchange.getResponse();
        return createHttpErrorResponse(serverHttpResponse, HttpStatus.UNAUTHORIZED, "Invalid API key detected.");
    }

    private Optional<String> getApiKeyByRequest(ServerHttpRequest serverHttpRequest) {
        if(!serverHttpRequest.getHeaders().containsKey(Constants.API_KEY_HEADER_NAME)) {
            return Optional.empty();
        }
        return Optional.ofNullable(serverHttpRequest.getHeaders().getFirst(Constants.API_KEY_HEADER_NAME));
    }
    
    private Mono<Void> createHttpErrorResponse(ServerHttpResponse serverHttpResponse, HttpStatus httpStatus, String message) {
        DataBuffer responseBody = null;
        try {
            serverHttpResponse.getHeaders().set("content-type", "application/json");
            serverHttpResponse.setStatusCode(httpStatus);
            ErrorResponse response = ErrorResponse.builder()
                    .code(httpStatus.value() + "")
                    .message(message)
                    .build();
            ObjectMapper objectMapper = new ObjectMapper();
            responseBody = serverHttpResponse.bufferFactory().wrap(objectMapper.writeValueAsBytes(response));

        } catch (JsonProcessingException e) {
            Mono.error(e);
        }
        assert responseBody != null;
        return serverHttpResponse.writeWith(Mono.just(responseBody));
    }
}