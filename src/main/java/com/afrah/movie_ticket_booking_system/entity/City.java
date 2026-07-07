package com.afrah.movie_ticket_booking_system.entity;

import java.util.UUID;

public class City {
    private String id;

    private String state;
    private String name;

    public City() {

    }

    public City(String name, String state) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.state = state;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
