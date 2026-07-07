package com.afrah.movie_ticket_booking_system.DataTransferObjects;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public class CreateBookingRequest {

    @NotBlank(message = "User ID is required")
    private String userId;

    @NotBlank(message = "Show ID is required")
    private String showId;

    @NotEmpty(message = "At least one seat must be selected")
    private List<String> seatIds;

    @Valid
    private CreatePaymentRequest payment;

    public CreateBookingRequest() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getShowId() {
        return showId;
    }

    public void setShowId(String showId) {
        this.showId = showId;
    }

    public List<String> getSeatIds() {
        return seatIds;
    }

    public void setSeatIds(List<String> seatIds) {
        this.seatIds = seatIds;
    }

    public CreatePaymentRequest getPayment() {
        return payment;
    }

    public void setPayment(CreatePaymentRequest payment) {
        this.payment = payment;
    }
}
// {
// "userId": "user-id",
// "showId": "show-id",
// "seatIds": [
// "A1",
// "A2"
// ],
// "payment": {
// "cardHolderName": "Luke Skywalker",
// "cardNumber": "1234567812345678",
// "expiryDate": "2030-10",
// "cvv": "123"
// }
// }