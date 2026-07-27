package com.beyza.tests;

import com.beyza.base.BaseTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class HealthCheckTest extends BaseTest {

    @Test
    void shouldGetAllBookings() {

        given()
                .when()
                .get("/booking")
                .then()
                .statusCode(200);
    }
}