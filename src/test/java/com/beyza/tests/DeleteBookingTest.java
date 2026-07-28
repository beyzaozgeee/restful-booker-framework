package com.beyza.tests;

import com.beyza.base.BaseTest;
import com.beyza.builders.BookingBuilder;
import com.beyza.client.AuthClient;
import com.beyza.client.BookingClient;
import com.beyza.models.Booking;
import com.beyza.models.CreateBookingResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Epic("Restful Booker API")
@Feature("Booking Management")
public class DeleteBookingTest extends BaseTest {

    @Test
    @DisplayName("Delete an existing booking successfully")
    @Story("Delete Booking")
    @Description("Creates a booking, deletes it using a valid token, and verifies that the delete request completes successfully.")
    @Severity(SeverityLevel.CRITICAL)
    void shouldDeleteBooking() {

        // Booking oluştur
        Booking booking = BookingBuilder.defaultBooking();
        CreateBookingResponse response =
                BookingClient.createBooking(booking);

        // Token al
        String token = AuthClient.getToken();

        // Booking'i sil
        BookingClient.deleteBooking(
                response.getBookingid(),
                token
        );
    }
}