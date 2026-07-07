package com.afrah.movie_ticket_booking_system.DataTransferObjects;

import com.afrah.movie_ticket_booking_system.enums.PaymentType;

public class UpiPaymentRequest implements PaymentRequest {

    private PaymentType paymentType = PaymentType.UPI;

    private String upiId;

    @Override
    public PaymentType getPaymentType() {
        return paymentType;
    }

    public String getUpiId() {
        return getUpiId();
    }

    public void setUpiId(String upiId) {
        this.upiId = upiId;
    }
}
