package com.afrah.movie_ticket_booking_system.entity;

import java.util.List;

import org.hibernate.annotations.UuidGenerator;

import com.afrah.movie_ticket_booking_system.enums.SeatStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @UuidGenerator
    private String bookingId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "show_id", nullable = false)
    private Show show;

    @ManyToMany
    @JoinTable(name = "booked_seats", joinColumns = @JoinColumn(name = "booking_id"), inverseJoinColumns = @JoinColumn(name = "seat_id"))
    private List<Seat> seats;

    private double totalAmount;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_id")
    private PaymentTransaction payment;

    public Booking() {
    }

    private Booking(User user, Show show, List<Seat> seats, double totalAmount, PaymentTransaction payment) {

        this.user = user;
        this.show = show;
        this.seats = seats;
        this.totalAmount = totalAmount;
        this.payment = payment;
    }

    @PrePersist
    public void confirmBooking() {
        for (Seat seat : seats) {
            seat.setStatus(SeatStatus.BOOKED);
        }
    }

    public String getBookingId() {
        return bookingId;
    }

    public User getUser() {
        return user;
    }

    public Show getShow() {
        return show;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public PaymentTransaction getPayment() {
        return payment;
    }

    // ===========================
    // Builder
    // ===========================

    public static class BookingBuilder {

        private User user;
        private Show show;
        private List<Seat> seats;
        private double totalAmount;
        private PaymentTransaction payment;

        public BookingBuilder setUser(User user) {
            this.user = user;
            return this;
        }

        public BookingBuilder setShow(Show show) {
            this.show = show;
            return this;
        }

        public BookingBuilder setSeats(List<Seat> seats) {
            this.seats = seats;
            return this;
        }

        public BookingBuilder setTotalAmount(double totalAmount) {
            this.totalAmount = totalAmount;
            return this;
        }

        public BookingBuilder setPayment(PaymentTransaction payment) {
            this.payment = payment;
            return this;
        }

        public Booking build() {

            if (user == null) {
                throw new IllegalStateException("User is required");
            }

            if (show == null) {
                throw new IllegalStateException("Show is required");
            }

            if (seats == null || seats.isEmpty()) {
                throw new IllegalStateException("At least one seat must be selected");
            }

            if (totalAmount <= 0) {
                throw new IllegalStateException("Total amount must be greater than zero");
            }

            if (payment == null) {
                throw new IllegalStateException("Payment is required");
            }

            return new Booking(
                    user,
                    show,
                    seats,
                    totalAmount,
                    payment);
        }
    }
}