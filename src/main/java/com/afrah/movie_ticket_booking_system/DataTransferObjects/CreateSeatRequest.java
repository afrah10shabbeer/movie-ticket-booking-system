package com.afrah.movie_ticket_booking_system.DataTransferObjects;

import com.afrah.movie_ticket_booking_system.enums.SeatType;

import jakarta.validation.constraints.NotBlank;

public class CreateSeatRequest {

    @NotBlank(message = "Seat number is required")
    private String seatNumber;

    @NotBlank(message = "Sear row is required")
    private int seatRow;

    @NotBlank(message = "Sear column is required")
    private int seatColumn;

    @NotBlank(message = "Sear type is required")
    private SeatType seatType;

    public CreateSeatRequest() {
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public int getSeatRow() {
        return seatRow;
    }

    public void setSeatRow(int seatRow) {
        this.seatRow = seatRow;
    }

    public int getSeatColumn() {
        return seatColumn;
    }

    public void setSeatColumn(int seatColumn) {
        this.seatColumn = seatColumn;
    }

    public SeatType getSeatType() {
        return seatType;
    }

    public void setSeatType(SeatType seatType) {
        this.seatType = seatType;
    }
}