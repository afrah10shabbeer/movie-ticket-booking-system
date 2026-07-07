package com.afrah.movie_ticket_booking_system.entity;

import com.afrah.movie_ticket_booking_system.enums.SeatStatus;
import com.afrah.movie_ticket_booking_system.enums.SeatType;

public class Seat {

    private String id;
    private int row;
    private int column;
    private SeatType seatType;
    private SeatStatus status;

    public Seat() {
    }

    public Seat(String id, int row, int col, SeatType seatType) {
        this.id = id;
        this.row = row;
        this.column = col;
        this.seatType = seatType;
        this.status = SeatStatus.AVAILABLE;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public SeatType getSeatType() {
        return seatType;
    }

    public void setSeatType(SeatType seatType) {
        this.seatType = seatType;
    }

    public SeatStatus getStatus() {
        return status;
    }

    public void setStatus(SeatStatus status) {
        this.status = status;
    }
}