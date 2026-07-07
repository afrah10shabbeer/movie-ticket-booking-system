package com.afrah.movie_ticket_booking_system.entity;

import java.util.UUID;

import com.afrah.movie_ticket_booking_system.pubsub.MoviePublisher;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class Movie {

    private String id;
    private String title;
    private String genre;
    private String language;
    private int durationInMinutes;

    private MoviePublisher publisher = new MoviePublisher();

    public Movie() {

    }

    public Movie(String title, int durationInMinutes, String genre, String language) {
        this.id = UUID.randomUUID().toString();
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

    public String getId() {
        return id;
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