package com.afrah.movie_ticket_booking_system.entity;

import org.hibernate.annotations.UuidGenerator;

import com.afrah.movie_ticket_booking_system.pubsub.MoviePublisher;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "movies")
public class Movie {

    @Id
    @UuidGenerator
    private String movieId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String genre;
    @Column(nullable = false)
    private String language;
    @Column(nullable = false)
    private int durationInMinutes;

    @Transient
    private MoviePublisher publisher = new MoviePublisher();

    public Movie() {

    }

    public Movie(String title, int durationInMinutes, String genre, String language) {
        this.title = title;
        this.durationInMinutes = durationInMinutes;
        this.genre = genre;
        this.language = language;
    }

    @JsonIgnore
    public MoviePublisher getMoviePublisher() {
        return publisher;
    }

    public void setMoviePublisher(MoviePublisher publisher) {
        this.publisher = publisher;
    }

    public String getMovieId() {
        return movieId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getDurationInMinutes() {
        return durationInMinutes;
    }

    public void setDurationInMinutes(int durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }
}