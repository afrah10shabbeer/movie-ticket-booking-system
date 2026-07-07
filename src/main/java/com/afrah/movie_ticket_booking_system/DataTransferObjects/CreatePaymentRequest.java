package com.afrah.movie_ticket_booking_system.DataTransferObjects;

import com.afrah.movie_ticket_booking_system.enums.PaymentType;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import io.swagger.v3.oas.annotations.media.Schema;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "paymentType", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = CreditCardPaymentRequest.class, name = "CREDIT_CARD"),
        @JsonSubTypes.Type(value = UpiPaymentRequest.class, name = "UPI")
})
@Schema(description = "Payment details. The request body depends on the selected payment type.")
public interface CreatePaymentRequest {

    @Schema(description = "Payment method", example = "CREDIT_CARD", allowableValues = { "CREDIT_CARD",
            "UPI" }, requiredMode = Schema.RequiredMode.REQUIRED)
    PaymentType getPaymentType();
}