package com.afrah.movie_ticket_booking_system.DataTransferObjects;

import java.time.YearMonth;

import com.afrah.movie_ticket_booking_system.enums.PaymentType;

public class CreditCardPaymentRequest implements PaymentRequest {

    private PaymentType paymentType = PaymentType.CREDIT_CARD;

    private String cardHolderName;
    private String cardNumber;
    private YearMonth expiryDate;
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
