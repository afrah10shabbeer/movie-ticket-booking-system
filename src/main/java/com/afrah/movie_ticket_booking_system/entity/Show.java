package com.afrah.movie_ticket_booking_system.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import com.afrah.movie_ticket_booking_system.strategy.Pricing.PricingStrategy;

/* 
 “Which movie is playing, where, at what time, and how ticket price should be calculated.” 
 eg: Coco – PVR: Screen 2 – 6:30 PM – ₹250
 */
public class Show {
    private String id;
    private Movie movie;
    private Screen screen;
    private LocalDateTime startTime;
    private PricingStrategy pricingStrategy;

    public Show() {

    }

    public Show(Movie movie, Screen screen, LocalDateTime startTime, PricingStrategy pricingStrategy) {
        this.id = UUID.randomUUID().toString();
        this.movie = movie;
        this.screen = screen;
        this.startTime = startTime;
        this.pricingStrategy = pricingStrategy;
    }

    public String getId() {
        return id;
    }

    public Movie getMovie() {
        return movie;
    }

    public Screen getScreen() {
        return screen;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public PricingStrategy getPricingStrategy() {
        return pricingStrategy;
    }
}
