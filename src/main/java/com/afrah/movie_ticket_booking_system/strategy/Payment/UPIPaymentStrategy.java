package com.afrah.movie_ticket_booking_system.strategy.Payment;

import java.util.UUID;

import com.afrah.movie_ticket_booking_system.entity.PaymentTransaction;
import com.afrah.movie_ticket_booking_system.enums.PaymentStatus;

public class UPIPaymentStrategy implements PaymentStrategy {

    private String upiId;

    public UPIPaymentStrategy(String upiId) {
        this.upiId = upiId;
    }

    private boolean isValidUpiId() {
        return upiId != null
                && upiId.matches("^[a-zA-Z0-9._-]+@[a-zA-Z]+$");
    }

    @Override
    public PaymentTransaction pay(double amount) {

        System.out.printf("Processing UPI payment of ₹%.2f%n", amount);

        if (!isValidUpiId()) {
            throw new IllegalArgumentException("Invalid UPI ID: Please enter a valid UPI ID");
        }

        // Simulate payment gateway interaction
        boolean paymentSuccess = Math.random() > 0.05; // 95% success rate

        return new PaymentTransaction(
                amount,
                paymentSuccess ? PaymentStatus.SUCCESS : PaymentStatus.FAILURE,
                "TXN_" + UUID.randomUUID());
    }
}