package com.afrah.movie_ticket_booking_system.entity;

import java.util.List;

import com.afrah.movie_ticket_booking_system.enums.SeatStatus;

public class Booking {
    private String id;
    private User user;
    private Show show;
    private List<Seat> seats;
    private double totalAmount;
    private PaymentTransaction payment;

    public Booking() {

    }

    // Private constructor to be used by the Builder
    public Booking(String id, User user, Show show, List<Seat> seats, double totalAmount, PaymentTransaction payment) {
        this.id = id;
        this.user = user;
        this.show = show;
        this.seats = seats;
        this.totalAmount = totalAmount;
        this.payment = payment;
    }

    public String getId() {
        return id;
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

    // Marks seats as BOOKED upon successful booking creation
    public void confirmBooking() {
        for (Seat seat : seats) {
            seat.setStatus(SeatStatus.BOOKED);
        }
    }

    // Static inner Builder class
    public static class BookingBuilder {
        private String id;
        private User user;
        private Show show;
        private List<Seat> seats;
        private double totalAmount;
        private PaymentTransaction payment;

        public BookingBuilder setId(String id) {
            this.id = id;
            return this;
        }

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

            return new Booking(id, user, show, seats, totalAmount, payment);
        }

        // Booking booking = Booking.builder()
        // .user(user)
        // .show(show)
        // .addSeat(seat1)
        // .addSeat(seat2)
        // .payment(payment)
        // .totalAmount(800)
        // .build();

    }
}
