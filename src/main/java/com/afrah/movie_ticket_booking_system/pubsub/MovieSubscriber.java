package com.afrah.movie_ticket_booking_system.pubsub;

import com.afrah.movie_ticket_booking_system.entity.Movie;

public interface MovieSubscriber {
    void update(Movie movie);
}
