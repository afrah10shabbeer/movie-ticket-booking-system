package com.afrah.movie_ticket_booking_system.enums;

public enum SeatType {

    REGULAR(200.0), // Standard seats, Base price
    PREMIUM(80.0), // Better view, middle rows +50
    RECLINER(300.0), // fully reclining luxury seats +100
    VIP(400.0), // Premium experience +200
    WHEELCHAIR(200.0); // Accessible seating, usually standard price

    private final double price;

    SeatType(double price) {
        this.price = price;
    }
    
    public double getPrice() {
        return price;
    }
}
