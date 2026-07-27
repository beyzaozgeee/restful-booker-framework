package com.beyza.tests;

import com.beyza.base.BaseTest;
import com.beyza.client.AuthClient;
import io.qameta.allure.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@Epic("Restful Booker API")
@Feature("Authentication")
public class AuthenticationTest extends BaseTest {

    @Test
    @Story("Get Token")
    @Description("Verifies that a valid token is returned when authenticating with correct credentials.")
    @Severity(SeverityLevel.CRITICAL)
    void shouldGetToken() {

        String token = AuthClient.getToken();

        Assertions.assertNotNull(token);
        Assertions.assertFalse(token.isEmpty());
    }
}