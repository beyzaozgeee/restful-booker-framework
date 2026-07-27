package com.beyza.client;

import com.beyza.models.AuthRequest;
import com.beyza.models.AuthResponse;

import static io.restassured.RestAssured.given;

public class AuthClient {

    public static String getToken() {

        AuthRequest request = new AuthRequest(
                "admin",
                "password123"
        );

        AuthResponse response = given()
                .contentType("application/json")
                .body(request)
                .when()
                .post("/auth")
                .then()
                .statusCode(200)
                .extract()
                .as(AuthResponse.class);

        return response.getToken();
    }
}