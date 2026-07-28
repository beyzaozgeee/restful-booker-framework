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
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Epic("Restful Booker API")
@Feature("Booking Management")
public class GetBookingTest extends BaseTest {

    @Test
    @DisplayName("Retrieve an existing booking successfully")
    @Story("Get Booking")
    @Description("Creates a booking, retrieves it by ID, and verifies the booking details.")
    @Severity(SeverityLevel.NORMAL)
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