package com.beyza.tests;

import com.beyza.base.BaseTest;
import com.beyza.builders.BookingBuilder;
import com.beyza.client.AuthClient;
import com.beyza.client.BookingClient;
import com.beyza.models.Booking;
import com.beyza.models.CreateBookingResponse;
import org.junit.jupiter.api.Test;

public class DeleteBookingTest extends BaseTest {

    @Test
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
