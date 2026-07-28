package com.beyza.tests;

import com.beyza.base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@Epic("Restful Booker API")
@Feature("Health Check")
public class HealthCheckTest extends BaseTest {

    @Test
    @DisplayName("Verify API health by retrieving all bookings")
    @Story("Get All Bookings")
    @Description("Verifies that the Restful Booker API is available by retrieving the list of all bookings.")
    @Severity(SeverityLevel.MINOR)
    void shouldGetAllBookings() {

        given()
                .when()
                .get("/booking")
                .then()
                .statusCode(200);
    }
}