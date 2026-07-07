package com.afrah.movie_ticket_booking_system.strategy.Pricing;

import org.springframework.stereotype.Component;

import com.afrah.movie_ticket_booking_system.enums.PricingType;

@Component
public class PricingStrategyFactory {

    public PricingStrategy getPricingStrategy(PricingType pricingType) {

        switch (pricingType.name().toUpperCase()) {

            case "NORMAL":
                return new NormalPricingStrategy();

            case "PREMIUM":
                return new PremiumPricingStrategy();

            case "DYNAMIC":
                return new DynamicPricingStrategy();

            default:
                throw new IllegalArgumentException(
                        "Unsupported pricing strategy: " + pricingType);
        }
    }
}
