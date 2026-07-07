package com.afrah.movie_ticket_booking_system.DataTransferObjects;

import com.afrah.movie_ticket_booking_system.enums.PaymentType;

public interface PaymentRequest {
    PaymentType getPaymentType();
}
