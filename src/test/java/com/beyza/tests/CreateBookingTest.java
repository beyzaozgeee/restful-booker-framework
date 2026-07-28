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
public class CreateBookingTest extends BaseTest {

    @Test
    @DisplayName("Create a new booking successfully")
    @Story("Create Booking")
    @Description("Creates a new booking and verifies that the booking data is correct.")
    @Severity(SeverityLevel.CRITICAL)
    void shouldCreateBooking() {

        Booking booking = BookingBuilder.defaultBooking();

        CreateBookingResponse response = BookingClient.createBooking(booking);

        Assertions.assertEquals(
                booking.getFirstname(),
                response.getBooking().getFirstname()
        );

        Assertions.assertEquals(
                booking.getLastname(),
                response.getBooking().getLastname()
        );

        Assertions.assertEquals(
                booking.getTotalprice(),
                response.getBooking().getTotalprice()
        );
    }
}