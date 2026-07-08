package com.afrah.movie_ticket_booking_system.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.afrah.movie_ticket_booking_system.DataTransferObjects.CreateScreenRequest;
import com.afrah.movie_ticket_booking_system.entity.City;
import com.afrah.movie_ticket_booking_system.entity.Screen;
import com.afrah.movie_ticket_booking_system.entity.Show;
import com.afrah.movie_ticket_booking_system.entity.Theatre;
import com.afrah.movie_ticket_booking_system.exception.ResourceNotFoundException;
import com.afrah.movie_ticket_booking_system.repository.TheatreRepository;

import jakarta.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class TheatreService {

    private final TheatreRepository theatreRepository;
    private final CityService cityService;

    private static final Logger logger = LoggerFactory.getLogger(TheatreService.class);

    public TheatreService(
            CityService cityService,
            TheatreRepository theatreRepository) {

        this.cityService = cityService;
        this.theatreRepository = theatreRepository;
    }

    @Transactional
    public Theatre addTheatre(
            String name,
            String cityId,
            List<CreateScreenRequest> screenRequests) {

        logger.info("Creating theatre '{}' in city ID '{}'",
                name, cityId);

        City city = cityService.getCityById(cityId);

        Theatre theatre = new Theatre();
        theatre.setName(name);
        theatre.setCity(city);

        List<Screen> screens = new java.util.ArrayList<>();

        for (CreateScreenRequest screenRequest : screenRequests) {

            Screen screen = new Screen();
            screen.setTheatre(theatre);

            List<com.afrah.movie_ticket_booking_system.entity.Seat> seats = new java.util.ArrayList<>();

            screenRequest.getSeats().forEach(seatRequest -> {

                com.afrah.movie_ticket_booking_system.entity.Seat seat = new com.afrah.movie_ticket_booking_system.entity.Seat();

                seat.setSeatNumber(seatRequest.getSeatNumber());
                seat.setSeatRow(seatRequest.getSeatRow());
                seat.setSeatColumn(seatRequest.getSeatColumn());
                seat.setSeatType(seatRequest.getSeatType());
                seat.setScreen(screen);

                seats.add(seat);
            });

            screen.setSeats(seats);

            screens.add(screen);
        }

        theatre.setScreens(screens);

        Theatre savedTheatre = theatreRepository.save(theatre);

        logger.info(
                "Theatre '{}' created successfully with ID '{}'",
                savedTheatre.getName(),
                savedTheatre.getTheatreId());

        return savedTheatre;
    }

    @Transactional
    public Theatre getTheatreById(String theatreId) {

        logger.info("Fetching theatre with ID '{}'", theatreId);

        Theatre theatre = theatreRepository.findById(theatreId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Theatre not found with id: " + theatreId));

        // Initialize lazy collection
        theatre.getScreens().size();

        return theatre;
    }

    @Transactional
    public List<Theatre> getAllTheatres() {

        logger.info("Fetching all theatres");

        List<Theatre> theatres = theatreRepository.findAll();

        // Initialize screens for every theatre
        theatres.forEach(theatre -> theatre.getScreens().size());

        return theatres;
    }

    @Transactional
    public Screen getScreenById(String screenId) {

        logger.info("Searching for screen with ID '{}'",
                screenId);

        List<Theatre> theatres = theatreRepository.findAll();

        for (Theatre theatre : theatres) {

            for (Screen screen : theatre.getScreens()) {

                if (screen.getScreenId().equals(screenId)) {

                    logger.info(
                            "Screen '{}' found in theatre '{}'",
                            screenId,
                            theatre.getName());

                    return screen;
                }
            }
        }

        throw new ResourceNotFoundException(
                "Screen not found with id: " + screenId);
    }

    @Transactional
    public Theatre findTheatreForShow(Show show) {

        logger.info(
                "Finding theatre for show '{}'",
                show.getShowId());

        List<Theatre> theatres = theatreRepository.findAll();

        for (Theatre theatre : theatres) {

            if (theatre.getScreens()
                    .contains(show.getScreen())) {

                logger.info(
                        "Theatre '{}' found for show '{}'",
                        theatre.getName(),
                        show.getShowId());

                return theatre;
            }
        }

        throw new ResourceNotFoundException(
                "Theatre not found for show id: "
                        + show.getShowId());
    }

    public void deleteTheatreById(String theatreId) {

        logger.info(
                "Deleting theatre with ID '{}'",
                theatreId);

        Theatre theatre = getTheatreById(theatreId);

        theatreRepository.delete(theatre);

        logger.info(
                "Theatre '{}' deleted successfully",
                theatre.getName());
    }
}