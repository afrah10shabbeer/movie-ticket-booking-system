package com.afrah.movie_ticket_booking_system.pubsub;

import java.util.ArrayList;
import java.util.List;

import com.afrah.movie_ticket_booking_system.entity.Movie;

public class MoviePublisher {
    private final List<MovieSubscriber> subscribers = new ArrayList<>();

    public void addSubscriber(MovieSubscriber subscriber) {
        subscribers.add(subscriber);
    }

    public void removeSubscriber(MovieSubscriber subscriber) {
        subscribers.remove(subscriber);
    }

    public void notifySubscribers(Movie movie) {
        for (MovieSubscriber subscriber : subscribers) {
            subscriber.update(movie);
        }
    }
}
