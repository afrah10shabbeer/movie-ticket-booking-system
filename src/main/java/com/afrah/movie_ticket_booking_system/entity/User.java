package com.afrah.movie_ticket_booking_system.entity;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {

    @Id
    @UuidGenerator
    private String userId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    public User() {

    }

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
