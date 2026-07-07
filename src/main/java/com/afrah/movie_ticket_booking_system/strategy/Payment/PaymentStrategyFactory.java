package com.afrah.movie_ticket_booking_system.strategy.Payment;

import com.afrah.movie_ticket_booking_system.DataTransferObjects.CreditCardPaymentRequest;
import com.afrah.movie_ticket_booking_system.DataTransferObjects.CreatePaymentRequest;
import com.afrah.movie_ticket_booking_system.DataTransferObjects.UpiPaymentRequest;
import com.afrah.movie_ticket_booking_system.enums.PaymentType;
import com.afrah.movie_ticket_booking_system.enums.PricingType;
import com.afrah.movie_ticket_booking_system.strategy.Pricing.DynamicPricingStrategy;
import com.afrah.movie_ticket_booking_system.strategy.Pricing.NormalPricingStrategy;
import com.afrah.movie_ticket_booking_system.strategy.Pricing.PremiumPricingStrategy;
import com.afrah.movie_ticket_booking_system.strategy.Pricing.PricingStrategy;

public class PaymentStrategyFactory {
    public PaymentStrategy getPaymentStrategy(CreatePaymentRequest request) {

        switch (request.getPaymentType()) {

            case CREDIT_CARD:
                CreditCardPaymentRequest cardRequest = (CreditCardPaymentRequest) request;

                return new CreditCardPaymentStrategy(
                        cardRequest.getCardHolderName(),
                        cardRequest.getCardNumber(),
                        cardRequest.getExpiryDate(),
                        cardRequest.getCvv());

            case UPI:
                UpiPaymentRequest upiRequest = (UpiPaymentRequest) request;

                return new UpiPaymentStrategy(upiRequest.getUpiId());

            default:
                throw new IllegalArgumentException("Unsupported payment type");
        }
    }
}
