package com.afrah.movie_ticket_booking_system.entity;

import org.hibernate.annotations.UuidGenerator;

import com.afrah.movie_ticket_booking_system.enums.SeatStatus;
import com.afrah.movie_ticket_booking_system.enums.SeatType;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "seats")
public class Seat {

    @Id
    @UuidGenerator
    @Column(nullable = false, updatable = false)
    private String seatId;

    @Column(nullable = false)
    private String seatNumber;

    @Column(nullable = false)
    private int seatRow;

    @Column(nullable = false)
    private int seatColumn;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SeatType seatType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SeatStatus status;

    @ManyToOne
    @JoinColumn(name = "screen_id", nullable = false)
    @JsonBackReference
    private Screen screen;

    public Seat() {
        this.status = SeatStatus.AVAILABLE;
    }

    public Seat(String seatNumber, int seatRow, int seatColumn, SeatType seatType) {
        this.seatNumber = seatNumber;
        this.seatRow = seatRow;
        this.seatColumn = seatColumn;
        this.seatType = seatType;
        this.status = SeatStatus.AVAILABLE;
    }

    public String getSeatId() {
        return seatId;
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

    public SeatStatus getStatus() {
        return status;
    }

    public void setStatus(SeatStatus status) {
        this.status = status;
    }

    public Screen getScreen() {
        return screen;
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }
}