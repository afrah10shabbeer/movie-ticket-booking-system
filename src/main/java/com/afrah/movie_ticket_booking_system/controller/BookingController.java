package com.afrah.movie_ticket_booking_system.controller;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.afrah.movie_ticket_booking_system.DataTransferObjects.CreateBookingRequest;
import com.afrah.movie_ticket_booking_system.DataTransferObjects.CreatePaymentRequest;
import com.afrah.movie_ticket_booking_system.entity.Booking;
import com.afrah.movie_ticket_booking_system.entity.Seat;
import com.afrah.movie_ticket_booking_system.entity.Show;
import com.afrah.movie_ticket_booking_system.entity.User;
import com.afrah.movie_ticket_booking_system.service.BookingService;
import com.afrah.movie_ticket_booking_system.service.ShowService;
import com.afrah.movie_ticket_booking_system.service.UserService;
import com.afrah.movie_ticket_booking_system.strategy.Payment.PaymentStrategy;
import com.afrah.movie_ticket_booking_system.strategy.Payment.PaymentStrategyFactory;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    private final BookingService bookingService;
    private final UserService userService;
    private final ShowService showService;

    public BookingController(BookingService bookingService, UserService userService, ShowService showService) {
        this.bookingService = bookingService;
        this.userService = userService;
        this.showService = showService;
    }

    @Operation(summary = "Create a booking", description = "Creates a booking after validating the selected seats and processing the payment.")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, content = @Content(examples = {
            @ExampleObject(name = "Credit Card", summary = "Book using Credit Card", value = """
                    {
                      "userId":"<userId>",
                      "showId":"<showId>",
                      "seatIds":["<seatId1>","<seatId2>"],
                      "payment":{
                        "paymentType":"CREDIT_CARD",
                        "cardHolderName":"Afrah Shabbeer",
                        "cardNumber":"4111111111111111",
                        "expiryDate":"2029-12",
                        "cvv":"123"
                      }
                    }
                    """),
            @ExampleObject(name = "UPI", summary = "Book using UPI", value = """
                    {
                      "userId":"<userId>",
                      "showId":"<showId>",
                      "seatIds":["<seatId1>","<seatId2>"],
                      "payment":{
                        "paymentType":"UPI",
                        "upiId":"afrah@okaxis"
                      }
                    }
                    """)
    }))
    @PostMapping
    public ResponseEntity<Booking> createBooking(
            @Valid @org.springframework.web.bind.annotation.RequestBody CreateBookingRequest request) {

        User user = userService.getUserById(request.getUserId());
        Show show = showService.getShowById(request.getShowId());

        List<Seat> seats = new ArrayList<>();

        for (Seat seat : show.getScreen().getSeats()) {
            if (request.getSeatIds().contains(seat.getId())) {
                seats.add(seat);
            }
        }

        CreatePaymentRequest paymentRequest = request.getPayment();

        PaymentStrategy paymentStrategy = new PaymentStrategyFactory().getPaymentStrategy(paymentRequest);

        Booking booking = bookingService.createBooking(
                user,
                show,
                seats,
                paymentStrategy);

        return ResponseEntity.status(HttpStatus.CREATED).body(booking);
    }
}