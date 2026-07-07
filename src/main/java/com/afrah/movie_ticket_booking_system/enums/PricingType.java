package com.afrah.movie_ticket_booking_system.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum PricingType {

    NORMAL,
    PREMIUM,
    DYNAMIC;

    @JsonCreator
    public static PricingType fromString(String value) {

        if (value == null) {
            return null;
        }

        return PricingType.valueOf(value.toUpperCase());
    }
}