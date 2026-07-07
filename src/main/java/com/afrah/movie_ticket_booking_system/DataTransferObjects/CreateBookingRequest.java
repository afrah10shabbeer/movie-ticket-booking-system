package com.afrah.movie_ticket_booking_system.DataTransferObjects;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public class CreateBookingRequest {

    @NotBlank(message = "User ID is required")
    @Schema(example = "<userId>")
    private String userId;

    @NotBlank(message = "Show ID is required")
    @Schema(example = "<showId>")
    private String showId;

    @NotEmpty(message = "At least one seat must be selected")
    @Schema(example = "[\"<seatId1>\", \"<seatId2>\"]")
    private List<String> seatIds;

    @Valid
    @Schema(description = "Payment details")
    private CreatePaymentRequest payment;

    public CreateBookingRequest() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getShowId() {
        return showId;
    }

    public void setShowId(String showId) {
        this.showId = showId;
    }

    public List<String> getSeatIds() {
        return seatIds;
    }

    public void setSeatIds(List<String> seatIds) {
        this.seatIds = seatIds;
    }

    public CreatePaymentRequest getPayment() {
        return payment;
    }

    public void setPayment(CreatePaymentRequest payment) {
        this.payment = payment;
    }
}