package com.beyza.client;

import com.beyza.models.Booking;
import com.beyza.models.CreateBookingResponse;

import static io.restassured.RestAssured.given;

public class BookingClient {

    public static CreateBookingResponse createBooking(Booking booking) {

        return given()
                .contentType("application/json")
                .body(booking)
                .when()
                .post("/booking")
                .then()
                .statusCode(200)
                .extract()
                .as(CreateBookingResponse.class);
    }

    public static Booking getBooking(int bookingId) {

        return given()
                .when()
                .get("/booking/" + bookingId)
                .then()
                .statusCode(200)
                .extract()
                .as(Booking.class);
    }

    public static Booking updateBooking(int bookingId, Booking booking, String token) {

        return given()
                .contentType("application/json")
                .cookie("token", token)
                .body(booking)
                .when()
                .put("/booking/" + bookingId)
                .then()
                .statusCode(200)
                .extract()
                .as(Booking.class);
    }

    public static void deleteBooking(int bookingId, String token) {

        given()
                .cookie("token", token)
                .when()
                .delete("/booking/" + bookingId)
                .then()
                .statusCode(201);
    }
}