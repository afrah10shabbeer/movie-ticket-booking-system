package com.afrah.movie_ticket_booking_system.entity;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "cities")
public class City {

    @Id
    @UuidGenerator
    private String cityId;

    @Column(nullable = false)
    private String state;

    @Column(nullable = false)
    private String name;

    public City() {

    }

    public City(String name, String state) {
        this.name = name;
        this.state = state;
    }

    public String getId() {
        return cityId;
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
