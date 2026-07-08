package com.afrah.movie_ticket_booking_system;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;

import com.afrah.movie_ticket_booking_system.entity.Movie;
import com.afrah.movie_ticket_booking_system.pubsub.MoviePublisher;
import com.afrah.movie_ticket_booking_system.pubsub.MovieSubscriber;

public class MoviePublisherTest {

    @Test
    void shouldNotifySubscriber() {

        MoviePublisher publisher = new MoviePublisher();

        MovieSubscriber subscriber = mock(MovieSubscriber.class);

        publisher.addSubscriber(subscriber);

        Movie movie = new Movie();
        movie.setTitle("Interstellar");

        publisher.notifySubscribers(movie);

        verify(subscriber, times(1)).update(movie);
    }

    @Test
    void shouldNotifyAllSubscribers() {

        MoviePublisher publisher = new MoviePublisher();

        MovieSubscriber subscriber1 = mock(MovieSubscriber.class);
        MovieSubscriber subscriber2 = mock(MovieSubscriber.class);

        publisher.addSubscriber(subscriber1);
        publisher.addSubscriber(subscriber2);

        Movie movie = new Movie();
        movie.setTitle("Avengers");

        publisher.notifySubscribers(movie);

        verify(subscriber1, times(1)).update(movie);
        verify(subscriber2, times(1)).update(movie);
    }

    @Test
    void shouldNotNotifyRemovedSubscriber() {

        MoviePublisher publisher = new MoviePublisher();

        MovieSubscriber subscriber = mock(MovieSubscriber.class);

        publisher.addSubscriber(subscriber);
        publisher.removeSubscriber(subscriber);

        Movie movie = new Movie();
        movie.setTitle("Batman");

        assertDoesNotThrow(() -> publisher.notifySubscribers(movie));

        verify(subscriber, never()).update(any());
    }
}
