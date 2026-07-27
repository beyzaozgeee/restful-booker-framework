package com.beyza.tests;

import com.beyza.base.BaseTest;
import com.beyza.builders.BookingBuilder;
import com.beyza.client.AuthClient;
import com.beyza.client.BookingClient;
import com.beyza.models.Booking;
import com.beyza.models.CreateBookingResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UpdateBookingTest extends BaseTest {

    @Test
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