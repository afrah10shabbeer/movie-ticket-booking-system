package com.afrah.movie_ticket_booking_system.strategy.Payment;

import java.time.YearMonth;
import java.util.UUID;

import com.afrah.movie_ticket_booking_system.entity.PaymentTransaction;
import com.afrah.movie_ticket_booking_system.enums.PaymentStatus;

public class CreditCardPaymentStrategy implements PaymentStrategy {
    
    private final String cardHolderName;
    private final String cardNumber;
    private final YearMonth expiryDate;
    private final String cvv;

    public CreditCardPaymentStrategy(String cardHolderName, String cardNumber, YearMonth expiryDate, String cvv) {
        this.cardHolderName = cardHolderName;
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
    }

    private boolean isValidCard() {
        return cardNumber != null
                && cardNumber.matches("\\d{16}")
                && cvv.matches("\\d{3}")
                && expiryDate.isAfter(YearMonth.now().minusMonths(1))
                && !cardHolderName.isBlank();
    }

    @Override
    public PaymentTransaction pay(double amount) {
        System.out.printf(
            "Processing credit card payment of $%.2f%n",
            amount
        );
        
        //Simulate payment gateway interaction
        if (!isValidCard()) {
            throw new IllegalArgumentException("Invalid Card details: Please enter valid card details");
        }
        boolean paymentSuccess = Math.random() > 0.05;  // 95% success rate
        return new PaymentTransaction(amount, paymentSuccess ? PaymentStatus.SUCCESS : PaymentStatus.FAILURE, "TXN_" + UUID.randomUUID());
    }
}
