package com.afrah.movie_ticket_booking_system.DataTransferObjects;

import com.afrah.movie_ticket_booking_system.enums.PaymentType;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class UpiPaymentRequest implements PaymentRequest {

    @Schema(description = "Payment method", example = "UPI", allowableValues = { "UPI" })
    private final PaymentType paymentType = PaymentType.UPI;

    @NotBlank(message = "UPI ID is required")
    @Pattern(regexp = "^[a-zA-Z0-9._-]+@[a-zA-Z]+$", message = "Please enter a valid UPI ID")
    @Schema(description = "UPI ID used for payment", example = "afrah@okaxis")
    private String upiId;

    @Override
    public PaymentType getPaymentType() {
        return paymentType;
    }

    public String getUpiId() {
        return upiId;
    }

    public void setUpiId(String upiId) {
        this.upiId = upiId;
    }
}