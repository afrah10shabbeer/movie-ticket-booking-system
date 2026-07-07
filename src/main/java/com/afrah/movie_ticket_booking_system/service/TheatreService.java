package com.afrah.movie_ticket_booking_system.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import com.afrah.movie_ticket_booking_system.entity.*;
import com.afrah.movie_ticket_booking_system.exception.ResourceNotFoundException;

@Service
public class TheatreService {

    private final Map<String, Theatre> theatres = new ConcurrentHashMap<>();

    private final CityService cityService;

    public TheatreService(CityService cityService) {
        this.cityService = cityService;
    }

    public Theatre addTheatre(String name, String cityId, List<Screen> screens) {

        City city = cityService.getCityById(cityId);

        if (city == null) {
            throw new ResourceNotFoundException("City not found with id: " + cityId);
        }

        Theatre theatre = new Theatre(
                name,
                city,
                screens);

        theatres.put(theatre.getId(), theatre);

        return theatre;
    }

    public Theatre getTheatreById(String theatreId) {
        if (theatres.containsKey(theatreId)) {
            return theatres.get(theatreId);
        }
        throw new ResourceNotFoundException("Theatre not found with id: " + theatreId);
    }

    public List<Theatre> getAllTheatres() {
        return new ArrayList<>(theatres.values());
    }

    public Screen getScreenById(String screenId) {

        for (Theatre theatre : theatres.values()) {
            for (Screen screen : theatre.getScreens()) {
                if (screen.getId().equals(screenId)) {
                    return screen;
                }
            }
        }

        throw new ResourceNotFoundException("Screen not found with id: " + screenId);
    }

    public Theatre findTheatreForShow(Show show) {

        for (Theatre theatre : theatres.values()) {
            if (theatre.getScreens().contains(show.getScreen())) {
                return theatre;
            }
        }

        throw new ResourceNotFoundException("Theatre not found for show with show id : " + show.getId());
    }

    public void deleteTheatreById(String theatreId) {
        Theatre theatre = theatres.remove(theatreId);

        if (theatre == null) {
            throw new ResourceNotFoundException("Theatre not found with id: " + theatreId);
        }
    }
}