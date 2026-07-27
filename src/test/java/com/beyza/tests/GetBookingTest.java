package com.beyza.tests;

import com.beyza.base.BaseTest;
import com.beyza.builders.BookingBuilder;
import com.beyza.client.BookingClient;
import com.beyza.models.Booking;
import com.beyza.models.CreateBookingResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GetBookingTest extends BaseTest {

    @Test
    void shouldGetBooking() {

        Booking booking = BookingBuilder.defaultBooking();

        CreateBookingResponse response =
                BookingClient.createBooking(booking);

        Booking bookingFromApi =
                BookingClient.getBooking(response.getBookingid());

        Assertions.assertEquals(
                booking.getFirstname(),
                bookingFromApi.getFirstname()
        );

        Assertions.assertEquals(
                booking.getLastname(),
                bookingFromApi.getLastname()
        );
    }
}