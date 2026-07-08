package com.afrah.movie_ticket_booking_system.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "theatres")
public class Theatre {

    @Id
    @UuidGenerator
    private String theatreId;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    private City city;

    @OneToMany(mappedBy = "theatre", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Screen> screens = new ArrayList<>();

    public Theatre() {
    }

    public Theatre(String name, City city, List<Screen> screens) {
        this.name = name;
        this.city = city;
        setScreens(screens);
    }

    public void addScreen(Screen screen) {
        screen.setTheatre(this);
        screens.add(screen);
    }

    public void removeScreen(Screen screen) {
        screens.remove(screen);
        screen.setTheatre(null);
    }

    public String getTheatreId() {
        return theatreId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public List<Screen> getScreens() {
        return screens;
    }

    public void setScreens(List<Screen> screens) {
        this.screens.clear();

        if (screens != null) {
            for (Screen screen : screens) {
                addScreen(screen);
            }
        }
    }
}