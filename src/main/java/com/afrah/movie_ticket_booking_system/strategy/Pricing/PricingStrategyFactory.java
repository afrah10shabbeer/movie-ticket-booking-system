package com.afrah.movie_ticket_booking_system.strategy.Pricing;

import org.springframework.stereotype.Component;

import com.afrah.movie_ticket_booking_system.enums.PricingType;

@Component
public class PricingStrategyFactory {

    private final NormalPricingStrategy normalPricingStrategy;
    private final PremiumPricingStrategy premiumPricingStrategy;
    private final DynamicPricingStrategy dynamicPricingStrategy;

    public PricingStrategyFactory(
            NormalPricingStrategy normalPricingStrategy,
            PremiumPricingStrategy premiumPricingStrategy,
            DynamicPricingStrategy dynamicPricingStrategy) {

        this.normalPricingStrategy = normalPricingStrategy;
        this.premiumPricingStrategy = premiumPricingStrategy;
        this.dynamicPricingStrategy = dynamicPricingStrategy;
    }

    public PricingStrategy getPricingStrategy(PricingType pricingType) {

        if (pricingType == null) {
            throw new IllegalArgumentException("Pricing type cannot be null");
        }

        return switch (pricingType) {

            case NORMAL -> normalPricingStrategy;

            case PREMIUM -> premiumPricingStrategy;

            case DYNAMIC -> dynamicPricingStrategy;

        };
    }
}