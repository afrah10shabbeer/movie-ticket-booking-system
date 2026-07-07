package com.afrah.movie_ticket_booking_system.service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import com.afrah.movie_ticket_booking_system.entity.*;
import com.afrah.movie_ticket_booking_system.enums.PaymentStatus;
import com.afrah.movie_ticket_booking_system.exception.PaymentFailedException;
import com.afrah.movie_ticket_booking_system.strategy.Payment.PaymentStrategy;

@Service
public class BookingService {

    private final SeatLockManager seatLockManager;
    private final Map<String, Booking> bookings = new ConcurrentHashMap<>();

    public BookingService(SeatLockManager seatLockManager) {
        this.seatLockManager = seatLockManager;
    }

    public Booking createBooking(User user, Show show, List<Seat> seats, PaymentStrategy paymentStrategy) {

        // 1. Lock the seats
        seatLockManager.lockSeats(show, seats, user.getId());

        // 2. Calcuate the total price
        double totalAmount = show.getPricingStrategy().calculatePrice(seats);

        // 3. Process Payment
        PaymentTransaction payment = paymentStrategy.pay(totalAmount);

        if (payment.getPaymentStatus() != PaymentStatus.SUCCESS) {
            seatLockManager.unlockSeats(show, seats, user.getId());

            throw new PaymentFailedException("Payment failed. Please try again");
        }

        // 4. If payment is successful, create the booking
        Booking booking = new Booking.BookingBuilder()
                .setUser(user)
                .setShow(show)
                .setSeats(seats)
                .setTotalAmount(totalAmount)
                .setPayment(payment)
                .build();

        // 5. Confirm the booking (mark sea ts as BOOKED)
        booking.confirmBooking();

        bookings.put(booking.getId(), booking);

        // Clean up the lock map
        seatLockManager.unlockSeats(show, seats, user.getId());
        return booking;

    }
}
