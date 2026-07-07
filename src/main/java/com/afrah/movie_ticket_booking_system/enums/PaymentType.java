package com.afrah.movie_ticket_booking_system.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum PaymentType {
    CREDIT_CARD,
    UPI;

    @JsonCreator
    public static PaymentType fromString(String value) {

        if (value == null) {
            return null;
        }

        return PaymentType.valueOf(value.toUpperCase());
    }
}
