package com.afrah.movie_ticket_booking_system.DataTransferObjects;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class CreatePaymentRequest {

    @NotBlank(message = "Card holder name is required")
    @Schema(example = "John Reese")
    private String cardHolderName;

    @NotBlank(message = "Card number is required")
    @Schema(example = "1234567891234567")
    @Pattern(regexp = "\\d{16}", message = "Card number must contain exactly 16 digits")
    private String cardNumber;

    @NotBlank(message = "Expiry date is required")
    @Schema(example = "2030-10")
    private String expiryDate;

    @NotBlank(message = "CVV is required")
    @Schema(example = "345")
    @Pattern(regexp = "\\d{3}", message = "CVV must contain exactly 3 digits")
    private String cvv;

    public CreatePaymentRequest() {
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

}