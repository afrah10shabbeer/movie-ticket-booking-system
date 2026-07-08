package com.afrah.movie_ticket_booking_system.entity;

import org.hibernate.annotations.UuidGenerator;

import com.afrah.movie_ticket_booking_system.enums.PaymentStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "payment_transactions")
public class PaymentTransaction {

    @Id
    @UuidGenerator
    private String paymentId;

    private String transactionId;

    private double amount;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    public PaymentTransaction() {
    }

    public PaymentTransaction(double amount, PaymentStatus paymentStatus, String transactionId) {
        this.amount = amount;
        this.paymentStatus = paymentStatus;
        this.transactionId = transactionId;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
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
}