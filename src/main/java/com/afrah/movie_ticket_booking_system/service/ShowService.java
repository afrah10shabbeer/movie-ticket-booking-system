package com.afrah.movie_ticket_booking_system.service;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.afrah.movie_ticket_booking_system.entity.Movie;
import com.afrah.movie_ticket_booking_system.entity.Screen;
import com.afrah.movie_ticket_booking_system.entity.Show;
import com.afrah.movie_ticket_booking_system.entity.Theatre;
import com.afrah.movie_ticket_booking_system.exception.ResourceNotFoundException;
import com.afrah.movie_ticket_booking_system.repository.ShowRepository;
import com.afrah.movie_ticket_booking_system.enums.PricingType;

@Service
public class ShowService {

    private final ShowRepository showRepository;

    private final MovieService movieService;
    private final TheatreService theatreService;

    private static final Logger logger = LoggerFactory.getLogger(ShowService.class);

    public ShowService(
            ShowRepository showRepository,
            MovieService movieService,
            TheatreService theatreService) {

        this.showRepository = showRepository;
        this.movieService = movieService;
        this.theatreService = theatreService;
    }

    public Show addShow(
            String movieId,
            String screenId,
            LocalDateTime startTime,
            PricingType pricingType) {

        logger.info(
                "Creating show for movie '{}' on screen '{}'",
                movieId,
                screenId);

        Movie movie = movieService.getMovieById(movieId);

        Screen screen = theatreService.getScreenById(screenId);

        Show show = new Show(
                movie,
                screen,
                startTime,
                pricingType);

        Show savedShow = showRepository.save(show);

        logger.info(
                "Show '{}' created successfully",
                savedShow.getShowId());

        return savedShow;
    }

    public Show getShowById(String showId) {

        return showRepository.findById(showId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Show not found with id: " + showId));
    }

    public List<Show> getAllShows() {

        return showRepository.findAll();

    }

    public void deleteShowById(String showId) {

        Show show = getShowById(showId);

        showRepository.delete(show);

        logger.info(
                "Show '{}' deleted successfully",
                showId);
    }

    public List<Show> findShows(
            String movieTitle,
            String cityName) {

        List<Show> allShows = showRepository.findByMovie_TitleIgnoreCase(movieTitle);

        return allShows.stream()
                .filter(show -> {

                    Theatre theatre = theatreService.findTheatreForShow(show);

                    return theatre != null &&
                            theatre.getCity()
                                    .getName()
                                    .equalsIgnoreCase(cityName);

                })
                .toList();
    }
}