package com.afrah.movie_ticket_booking_system.DataTransferObjects;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

public class CreateScreenRequest {

    @Valid
    @NotEmpty(message = "At least one seat is required")
    private List<CreateSeatRequest> seats;

    public CreateScreenRequest() {
    }

    public List<CreateSeatRequest> getSeats() {
        return seats;
    }

    public void setSeats(List<CreateSeatRequest> seats) {
        this.seats = seats;
    }
}