package com.afrah.movie_ticket_booking_system.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.afrah.movie_ticket_booking_system.entity.*;
import com.afrah.movie_ticket_booking_system.enums.PaymentStatus;
import com.afrah.movie_ticket_booking_system.exception.PaymentFailedException;
import com.afrah.movie_ticket_booking_system.repository.BookingRepository;
import com.afrah.movie_ticket_booking_system.strategy.Payment.PaymentStrategy;
import com.afrah.movie_ticket_booking_system.strategy.Pricing.PricingStrategy;
import com.afrah.movie_ticket_booking_system.strategy.Pricing.PricingStrategyFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class BookingService {

        private static final Logger logger = LoggerFactory.getLogger(BookingService.class);

        private final SeatLockManager seatLockManager;
        private final PricingStrategyFactory pricingStrategyFactory;
        private final BookingRepository bookingRepository;

        public BookingService(SeatLockManager seatLockManager,
                        PricingStrategyFactory pricingStrategyFactory,
                        BookingRepository bookingRepository) {

                this.seatLockManager = seatLockManager;
                this.pricingStrategyFactory = pricingStrategyFactory;
                this.bookingRepository = bookingRepository;
        }

        public Booking createBooking(User user, Show show, List<Seat> seats, PaymentStrategy paymentStrategy) {

                logger.info("Starting booking for user '{}' for show '{}'.", user.getUserId(), show.getShowId());

                // 1. Lock seats
                seatLockManager.lockSeats(
                                show,
                                seats,
                                user.getUserId());

                logger.info("Successfully locked {} seat(s) for user '{}'.", seats.size(), user.getUserId());

                // 2. Get pricing strategy dynamically
                PricingStrategy pricingStrategy = pricingStrategyFactory
                                .getPricingStrategy(show.getPricingType());

                // 3. Calculate price
                double totalAmount = pricingStrategy.calculatePrice(seats);

                logger.info("Calculated total booking amount: ₹{}", totalAmount);

                // 4. Process payment
                PaymentTransaction payment = paymentStrategy.pay(totalAmount);

                if (payment.getPaymentStatus() != PaymentStatus.SUCCESS) {

                        logger.warn("Payment failed for user '{}'. Unlocking seats.", user.getUserId());

                        seatLockManager.unlockSeats(
                                        show,
                                        seats,
                                        user.getUserId());

                        throw new PaymentFailedException("Payment failed. Please try again");
                }

                logger.info("Payment successful. Transaction ID: {}", payment.getTransactionId());

                // 5. Create booking
                Booking booking = new Booking.BookingBuilder()
                                .setUser(user)
                                .setShow(show)
                                .setSeats(seats)
                                .setTotalAmount(totalAmount)
                                .setPayment(payment)
                                .build();

                booking.confirmBooking();

                booking = bookingRepository.save(booking);

                // Release temporary locks
                seatLockManager.unlockSeats(
                                show,
                                seats,
                                user.getUserId());

                logger.info("Booking '{}' completed successfully", booking.getBookingId());

                return booking;
        }
}