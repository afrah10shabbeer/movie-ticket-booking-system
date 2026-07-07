package com.afrah.movie_ticket_booking_system.exception;

public class SeatNotAvailableException extends RuntimeException {

    public SeatNotAvailableException(String message) {
        super(message);
    }
}
