package com.afrah.movie_ticket_booking_system.strategy.Pricing;

import java.util.List;

import com.afrah.movie_ticket_booking_system.entity.Seat;

public class DynamicPricingStrategy implements PricingStrategy {

    @Override
    public double calculatePrice(List<Seat> seats) {

        double multiplier = 1.0;

        int selectedSeats = seats.size();

        if (selectedSeats >= 10) {
            multiplier = 1.5;
        } else if (selectedSeats >= 5) {
            multiplier = 1.25;
        }

        double totalPrice = 0;

        for (Seat seat : seats) {
            totalPrice += seat.getSeatType().getPrice() * multiplier;
        }

        return totalPrice;
    }
}