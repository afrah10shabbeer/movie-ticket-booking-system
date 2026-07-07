package com.afrah.movie_ticket_booking_system.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Service;

import com.afrah.movie_ticket_booking_system.entity.*;
import com.afrah.movie_ticket_booking_system.exception.ResourceNotFoundException;
import com.afrah.movie_ticket_booking_system.strategy.Pricing.PricingStrategy;

@Service
public class ShowService {

    private final Map<String, Show> shows = new ConcurrentHashMap<>();

    private final MovieService movieService;
    private final TheatreService theatreService;

    public ShowService(MovieService movieService, TheatreService theatreService) {
        this.movieService = movieService;
        this.theatreService = theatreService;
    }

    public Show addShow(String movieId,
            String screenId,
            LocalDateTime startTime,
            PricingStrategy pricingStrategy) {

        Movie movie = movieService.getMovieById(movieId);
        Screen screen = theatreService.getScreenById(screenId);

        if (movie == null) {
            throw new ResourceNotFoundException("Movie not found with id: " + movieId);
        }

        if (screen == null) {
            throw new ResourceNotFoundException("Screen not found with id: " + screenId);
        }

        Show show = new Show(
                movie,
                screen,
                startTime,
                pricingStrategy);

        shows.put(show.getId(), show);

        return show;
    }

    public Show getShowById(String showId) {
        if (shows.containsKey(showId)) {
            return shows.get(showId);
        }
        throw new ResourceNotFoundException("Show not found with id: " + showId);
    }

    public List<Show> getAllShows() {
        return new ArrayList<>(shows.values());
    }

    public void deleteShowById(String showId) {
        Show show = shows.remove(showId);

        if (show == null) {
            throw new ResourceNotFoundException("Show not found with id: " + showId);
        }
    }

    public List<Show> findShows(String movieTitle, String cityName) {
        List<Show> result = new ArrayList<>();

        for (Show show : shows.values()) {

            // Is it the movie the user searched?
            if (!show.getMovie().getTitle().equalsIgnoreCase(movieTitle)) {
                continue;
            }

            // Which theatre is this show running in?
            Theatre theatre = theatreService.findTheatreForShow(show);

            // Is that theatre in the requested city?
            if (theatre != null &&
                    theatre.getCity().getName().equalsIgnoreCase(cityName)) {
                result.add(show);
            }
        }

        return result;
    }
}
