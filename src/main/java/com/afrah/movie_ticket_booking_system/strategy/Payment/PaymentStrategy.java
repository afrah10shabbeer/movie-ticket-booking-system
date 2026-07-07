package com.afrah.movie_ticket_booking_system.strategy.Payment;

import com.afrah.movie_ticket_booking_system.entity.PaymentTransaction;

public interface PaymentStrategy {
    PaymentTransaction pay(double amount);
}
