package com.afrah.movie_ticket_booking_system.strategy.Pricing;

import java.util.List;

import com.afrah.movie_ticket_booking_system.entity.Seat;

public interface PricingStrategy {
    double calculatePrice(List<Seat> seats);
}
