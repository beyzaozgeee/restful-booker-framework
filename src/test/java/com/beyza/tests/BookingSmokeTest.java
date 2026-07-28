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
@Feature("Smoke Test")
public class BookingSmokeTest extends BaseTest {

    @Test
    @DisplayName("Complete Booking Lifecycle")
    @Story("End-to-End CRUD Flow")
    @Description("Creates, retrieves, updates, deletes a booking and verifies that it is no longer accessible.")
    @Severity(SeverityLevel.BLOCKER)
    void shouldExecuteCompleteBookingLifecycle() {

        // 1. Token al
        String token = AuthClient.getToken();
        Assertions.assertNotNull(token);

        // 2. Booking oluştur
        Booking booking = BookingBuilder.defaultBooking();

        CreateBookingResponse createResponse =
                BookingClient.createBooking(booking);

        Integer bookingId = createResponse.getBookingid();

        Assertions.assertNotNull(bookingId);

        // 3. Bookingi getir (retry ile - eventual consistency için)
        Booking bookingFromApi = getBookingWithRetry(bookingId, 3, 500);

        Assertions.assertEquals(
                booking.getFirstname(),
                bookingFromApi.getFirstname()
        );

        Assertions.assertEquals(
                booking.getLastname(),
                bookingFromApi.getLastname()
        );

        // 4. Güncelle
        Booking updatedBooking = BookingBuilder.defaultBooking();
        updatedBooking.setFirstname("Beyza");
        updatedBooking.setLastname("Abay");

        Booking updatedResult =
                BookingClient.updateBooking(
                        bookingId,
                        updatedBooking,
                        token
                );

        Assertions.assertEquals(
                "Beyza",
                updatedResult.getFirstname()
        );

        Assertions.assertEquals(
                "Abay",
                updatedResult.getLastname()
        );

        // 5. Sil
        BookingClient.deleteBooking(
                bookingId,
                token
        );

        // 6. Silindiğini doğrula
        try {

            BookingClient.getBooking(bookingId);

            Assertions.fail("Booking should have been deleted.");

        } catch (Exception ignored) {
            // Expected behavior (404 Not Found)
        }
    }

    /**
     * Retrieves a booking with retry logic to handle eventual consistency
     * on the demo API (booking may not be immediately queryable right after creation).
     */
    private Booking getBookingWithRetry(int bookingId, int maxAttempts, long delayMillis) {

        for (int attempt = 1; attempt <= maxAttempts; attempt++) {
            try {
                return BookingClient.getBooking(bookingId);
            } catch (AssertionError | Exception e) {
                if (attempt == maxAttempts) {
                    throw new RuntimeException(
                            "Booking with ID " + bookingId + " could not be retrieved after " + maxAttempts + " attempts.",
                            e
                    );
                }
                try {
                    Thread.sleep(delayMillis);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
            }
        }

        throw new IllegalStateException("Unreachable code");
    }
}

