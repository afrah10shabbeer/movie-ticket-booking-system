package com.afrah.movie_ticket_booking_system.pubsub;

import com.afrah.movie_ticket_booking_system.entity.Movie;
import com.afrah.movie_ticket_booking_system.entity.User;

public class UserSubscriber implements MovieSubscriber {

    private final User user;
    
    public UserSubscriber(User user) {
        this.user = user;
    }

    @Override
    public void update(Movie movie) {
        System.out.printf("Notification for  %s (%s): Movie '%s' is now available for booking!\n",
            user.getName(), user.getId(), movie.getTitle()
        );
    }
}
