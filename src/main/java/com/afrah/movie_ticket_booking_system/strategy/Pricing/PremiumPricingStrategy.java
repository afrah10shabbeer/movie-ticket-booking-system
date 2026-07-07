package com.afrah.movie_ticket_booking_system.strategy.Pricing;

import java.util.List;

import com.afrah.movie_ticket_booking_system.entity.Seat;

public class PremiumPricingStrategy implements PricingStrategy {

    private static final double PREMIUM_MULTIPLIER = 1.5;

    @Override
    public double calculatePrice(List<Seat> seats) {

        double totalPrice = 0;

        for (Seat seat : seats) {
            totalPrice += seat.getSeatType().getPrice() * PREMIUM_MULTIPLIER;
        }

        return totalPrice;
    }
}