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
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Epic("Restful Booker API")
@Feature("Booking Management")
public class UpdateBookingTest extends BaseTest {

    @Test
    @DisplayName("Update an existing booking successfully")
    @Story("Update Booking")
    @Description("Creates a booking, updates its details, and verifies that the booking information is updated successfully.")
    @Severity(SeverityLevel.CRITICAL)
    void shouldUpdateBooking() {

        // Booking oluştur
        Booking booking = BookingBuilder.defaultBooking();
        CreateBookingResponse response = BookingClient.createBooking(booking);

        // Token al
        String token = AuthClient.getToken();

        // Güncellenecek booking
        Booking updatedBooking = BookingBuilder.defaultBooking();
        updatedBooking.setFirstname("Beyza");
        updatedBooking.setLastname("Abay");

        // Güncelle
        Booking result = BookingClient.updateBooking(
                response.getBookingid(),
                updatedBooking,
                token
        );

        // Doğrula
        Assertions.assertEquals("Beyza", result.getFirstname());
        Assertions.assertEquals("Abay", result.getLastname());
    }
}