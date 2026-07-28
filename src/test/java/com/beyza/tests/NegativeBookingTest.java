package com.beyza.tests;

import com.beyza.base.BaseTest;
import com.beyza.builders.BookingBuilder;
import com.beyza.client.BookingClient;
import com.beyza.models.Booking;
import com.beyza.models.CreateBookingResponse;
import io.restassured.internal.http.HttpResponseException;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class NegativeBookingTest extends BaseTest {

    @Test
    void shouldReturn404ForInvalidBookingId() {

        try {
            Response response = given()
                    .when()
                    .get("/booking/999999999");

            // Exception fırlamadıysa (Linux/normal davranış), status code'u doğrudan kontrol et
            Assertions.assertEquals(404, response.getStatusCode());

        } catch (Exception e) {
            // Exception fırladıysa (Windows'taki özel davranış), içinden status code'u çıkar
            if (e instanceof HttpResponseException hre) {
                Assertions.assertEquals(404, hre.getStatusCode());
            } else {
                throw new RuntimeException(e);
            }
        }
    }

    @Test
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