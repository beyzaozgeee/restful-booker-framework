package com.beyza.tests;

import com.beyza.base.BaseTest;
import com.beyza.builders.BookingBuilder;
import com.beyza.client.BookingClient;
import com.beyza.models.Booking;
import com.beyza.models.CreateBookingResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.internal.http.HttpResponseException;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@Epic("Restful Booker API")
@Feature("Booking Management")
public class NegativeBookingTest extends BaseTest {

    @Test
    @DisplayName("Return 404 for a non-existing booking ID")
    @Story("Invalid Booking ID")
    @Description("Verifies that the API returns HTTP 404 when requesting a booking that does not exist.")
    @Severity(SeverityLevel.NORMAL)
    void shouldReturn404ForInvalidBookingId() {

        try {
            Response response = given()
                    .when()
                    .get("/booking/999999999");

            Assertions.assertEquals(404, response.getStatusCode());

        } catch (Exception e) {
            if (e instanceof HttpResponseException hre) {
                Assertions.assertEquals(404, hre.getStatusCode());
            } else {
                throw new RuntimeException(e);
            }
        }
    }

    @Test
    @DisplayName("Return 403 for an invalid authentication token")
    @Story("Invalid Authentication")
    @Description("Verifies that the API returns HTTP 403 when attempting to update a booking with an invalid authentication token.")
    @Severity(SeverityLevel.NORMAL)
    void shouldReturn403ForInvalidToken() {

        Booking booking = BookingBuilder.defaultBooking();

        CreateBookingResponse createResponse =
                BookingClient.createBooking(booking);

        Booking updatedBooking = BookingBuilder.defaultBooking();
        updatedBooking.setFirstname("Hacker");

        try {
            Response response = given()
                    .contentType("application/json")
                    .cookie("token", "invalidToken123")
                    .body(updatedBooking)
                    .when()
                    .put("/booking/" + createResponse.getBookingid());

            Assertions.assertEquals(403, response.getStatusCode());

        } catch (Exception e) {
            if (e instanceof HttpResponseException hre) {
                Assertions.assertEquals(403, hre.getStatusCode());
            } else {
                throw new RuntimeException(e);
            }
        }
    }
}