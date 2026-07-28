package com.beyza.tests;

import com.beyza.base.BaseTest;
import com.beyza.client.AuthClient;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Epic("Restful Booker API")
@Feature("Authentication")
public class AuthenticationTest extends BaseTest {

    @Test
    @DisplayName("Generate authentication token successfully")
    @Story("Generate Token")
    @Description("Verifies that a valid token is returned when authenticating with correct credentials.")
    @Severity(SeverityLevel.CRITICAL)
    void shouldGetToken() {

        String token = AuthClient.getToken();

        Assertions.assertNotNull(token);
        Assertions.assertFalse(token.isEmpty());
    }
}