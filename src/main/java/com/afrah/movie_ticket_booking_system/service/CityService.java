package com.afrah.movie_ticket_booking_system.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.afrah.movie_ticket_booking_system.entity.City;
import com.afrah.movie_ticket_booking_system.exception.ResourceNotFoundException;
import com.afrah.movie_ticket_booking_system.repository.CityRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Optional;

@Service
public class CityService {

    private final CityRepository cityRepository;

    private static final Logger logger = LoggerFactory.getLogger(CityService.class);

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public City addCity(City city) {

        logger.info("Adding city '{}'.", city.getName());

        City savedCity = cityRepository.save(city);

        logger.info("City '{}' added successfully with ID '{}'.",
                savedCity.getName(), savedCity.getId());

        return savedCity;
    }

    public List<City> getAllCities() {

        logger.info("Fetching all cities.");

        return cityRepository.findAll();
    }

    public City getCityById(String cityId) {

        logger.info("Fetching city with ID '{}'.", cityId);

        Optional<City> city = cityRepository.findById(cityId);

        if (city.isPresent()) {
            logger.info("City '{}' fetched successfully.", city.get().getName());
            return city.get();
        }

        logger.warn("City not found with ID '{}'.", cityId);

        throw new ResourceNotFoundException("City not found with id: " + cityId);
    }

    public void deleteCityById(String cityId) {

        logger.info("Deleting city with ID '{}'.", cityId);

        City city = getCityById(cityId);

        cityRepository.delete(city);

        logger.info("City '{}' deleted successfully.", city.getName());
    }

}
