package com.afrah.movie_ticket_booking_system.entity;

import java.util.UUID;

import com.afrah.movie_ticket_booking_system.enums.PaymentStatus;

public class PaymentTransaction {
    private String id;
    private String transactionId;

    private double amount;
    private PaymentStatus paymentStatus;

    public PaymentTransaction() {

    }

    public PaymentTransaction(double amount, PaymentStatus paymentStatus, String transactionId) {
        this.id = UUID.randomUUID().toString();
        this.amount = amount;
        this.paymentStatus = paymentStatus;
        this.transactionId = transactionId;
    }

    public String getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getTransactionId() {
        return transactionId;
    }
}
