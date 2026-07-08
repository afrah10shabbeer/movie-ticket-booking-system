package com.afrah.movie_ticket_booking_system.entity;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.UuidGenerator;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.CascadeType;

@Entity
@Table(name = "screens")
public class Screen {

    @Id
    @UuidGenerator
    @Column(nullable = false, updatable = false)
    private String screenId;

    @OneToMany(mappedBy = "screen", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Seat> seats = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "theatre_id")
    @JsonBackReference
    private Theatre theatre;

    public Screen() {
    }

    public String getScreenId() {
        return screenId;
    }

    public Theatre getTheatre() {
        return theatre;
    }

    public void setTheatre(Theatre theatre) {
        this.theatre = theatre;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {

        this.seats.clear();

        if (seats != null) {
            seats.forEach(this::addSeat);
        }
    }

    public void addSeat(Seat seat) {
        seats.add(seat);
        seat.setScreen(this);
    }

    public void removeSeat(Seat seat) {
        seats.remove(seat);
        seat.setScreen(null);
    }
}