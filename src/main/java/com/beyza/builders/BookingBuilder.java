package com.beyza.builders;

import com.beyza.models.Booking;
import com.beyza.models.BookingDates;
import net.datafaker.Faker;

public class BookingBuilder {

    private static final Faker faker = new Faker();

    public static Booking defaultBooking() {

        BookingDates dates = new BookingDates(
                "2026-08-01",
                "2026-08-10"
        );

        return new Booking(
                faker.name().firstName(),
                faker.name().lastName(),
                faker.number().numberBetween(50, 1000),
                true,
                dates,
                faker.food().dish()
        );
    }
}