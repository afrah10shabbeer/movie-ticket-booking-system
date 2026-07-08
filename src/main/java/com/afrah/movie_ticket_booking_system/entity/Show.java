package com.afrah.movie_ticket_booking_system.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.UuidGenerator;

import com.afrah.movie_ticket_booking_system.enums.PricingType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "shows")
public class Show {

    @Id
    @UuidGenerator
    @Column(nullable = false, updatable = false)
    private String showId;

    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "screen_id", nullable = false)
    private Screen screen;

    @Column(nullable = false)
    private LocalDateTime startTime;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PricingType pricingType;

    public Show() {
    }

    public Show(Movie movie, Screen screen, LocalDateTime startTime, PricingType pricingType) {
        this.movie = movie;
        this.screen = screen;
        this.startTime = startTime;
        this.pricingType = pricingType;
    }

    public String getShowId() {
        return showId;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Screen getScreen() {
        return screen;
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public PricingType getPricingType() {
        return pricingType;
    }

    public void setPricingType(PricingType pricingType) {
        this.pricingType = pricingType;
    }
}