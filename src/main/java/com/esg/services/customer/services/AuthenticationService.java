package com.esg.services.customer.services;

import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    public boolean isApiKeyValid(String apiKey) {
        return apiKey.equals("1234");
    }
}
