package com.afrah.movie_ticket_booking_system.entity;

import java.util.List;
import java.util.UUID;

public class Theatre {
    private String id;
    private String name; // PVR-INOX
    private City city; // city
    private List<Screen> screens; // screen1, screen2 ..

    public Theatre() {

    }

    public Theatre(String name, City city, List<Screen> screens) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.city = city;
        this.screens = screens;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public City getCity() {
        return city;
    }

    public List<Screen> getScreens() {
        return screens;
    }
}
