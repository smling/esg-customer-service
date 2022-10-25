package com.esg.services.customer.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.platform.commons.util.ReflectionUtils;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AuthenticationServiceTest {
    private static final String TEST_API_KEY = "12345";
    @InjectMocks
    AuthenticationService authenticationService;

    static Stream<Arguments> mockIsApiKeyValidTestCaseData() {
        return Stream.of(
                Arguments.of("Correct API key", TEST_API_KEY, true),
                Arguments.of("Incorrect API key", "Incorrect Key", false)
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("mockIsApiKeyValidTestCaseData")
    @DisplayName("Given valid data, when call isApiKeyValid, then return correct boolean result")
    void isApiKeyValid(String description, String key, boolean expectedResult) {
        ReflectionTestUtils.setField(authenticationService, "defaultApiKey", TEST_API_KEY);
        Assertions.assertDoesNotThrow(()-> {
            boolean result = authenticationService.isApiKeyValid(key);
            Assertions.assertEquals(expectedResult, result);
        });
    }
}