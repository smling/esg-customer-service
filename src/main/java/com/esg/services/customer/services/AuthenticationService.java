package com.esg.services.customer.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    String defaultApiKey;
    public AuthenticationService(@Value("${default.api.key}") String defaultApiKey) {
        this.defaultApiKey = defaultApiKey;
    }
    public boolean isApiKeyValid(String apiKey) {
        return apiKey.equals(defaultApiKey);
    }
}
