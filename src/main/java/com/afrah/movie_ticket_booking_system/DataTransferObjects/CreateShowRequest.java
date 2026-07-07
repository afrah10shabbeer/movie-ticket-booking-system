package com.afrah.movie_ticket_booking_system.DataTransferObjects;

import java.time.LocalDateTime;

import com.afrah.movie_ticket_booking_system.enums.PricingType;
import com.afrah.movie_ticket_booking_system.strategy.Pricing.PricingStrategy;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateShowRequest {

    @NotBlank(message = "Movie ID is required")
    @Schema(example = "<movieId>")
    private String movieId;

    @NotBlank(message = "Screen ID is required")
    @Schema(example = "<screenId>")
    private String screenId;

    @NotNull(message = "Start time is required")
    @Schema(example = "2026-07-08T18:00:00", description = "Show start date and time in ISO-8601 format")
    private LocalDateTime startTime;

    @NotNull(message = "Pricing strategy must not be null")
    @Schema(description = "Pricing strategy used to calculate the final ticket price. Supported values: NORMAL, PREMIUM, DYNAMIC", example = "NORMAL", requiredMode = Schema.RequiredMode.REQUIRED)
    private PricingType pricingType;

    public CreateShowRequest() {
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getScreenId() {
        return screenId;
    }

    public void setScreenId(String screenId) {
        this.screenId = screenId;
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
}