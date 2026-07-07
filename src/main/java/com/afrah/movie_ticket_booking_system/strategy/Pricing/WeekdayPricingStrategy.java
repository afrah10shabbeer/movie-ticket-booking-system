package com.afrah.movie_ticket_booking_system.strategy.Pricing;

import java.util.List;

import com.afrah.movie_ticket_booking_system.entity.Seat;

public class WeekdayPricingStrategy implements PricingStrategy {

    @Override
    public double calculatePrice(List<Seat> seats) {

        double totalPrice = 0.0;

        for (Seat seat : seats) {
            totalPrice += seat.getSeatType().getPrice();
        }

        return totalPrice;
    }
}
