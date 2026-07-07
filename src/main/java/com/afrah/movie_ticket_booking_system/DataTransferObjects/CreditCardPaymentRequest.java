package com.afrah.movie_ticket_booking_system.DataTransferObjects;

import java.time.YearMonth;

import com.afrah.movie_ticket_booking_system.enums.PaymentType;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class CreditCardPaymentRequest implements CreatePaymentRequest {

    @Schema(description = "Payment method", example = "CREDIT_CARD", allowableValues = {
            "CREDIT_CARD" }, requiredMode = Schema.RequiredMode.REQUIRED)
    private final PaymentType paymentType = PaymentType.CREDIT_CARD;

    @NotBlank(message = "Card holder name is required")
    @Schema(description = "Name of the card holder", example = "Afrah Shabbeer")
    private String cardHolderName;

    @NotBlank(message = "Card number is required")
    @Pattern(regexp = "\\d{16}", message = "Card number must contain exactly 16 digits")
    @Schema(description = "16-digit credit card number", example = "4111111111111111")
    private String cardNumber;

    @NotNull(message = "Expiry date is required")
    @Schema(description = "Card expiry date in yyyy-MM format", example = "2029-12")
    private YearMonth expiryDate;

    @NotBlank(message = "CVV is required")
    @Pattern(regexp = "\\d{3}", message = "CVV must contain exactly 3 digits")
    @Schema(description = "3-digit card verification value", example = "123")
    private String cvv;

    @Override
    public PaymentType getPaymentType() {
        return paymentType;
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

    public YearMonth getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(YearMonth expireyDate) {
        this.expiryDate = expireyDate;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

}
