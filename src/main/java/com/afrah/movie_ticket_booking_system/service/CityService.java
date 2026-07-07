package com.afrah.movie_ticket_booking_system.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import com.afrah.movie_ticket_booking_system.entity.City;
import com.afrah.movie_ticket_booking_system.exception.ResourceNotFoundException;

@Service
public class CityService {

    private final Map<String, City> cities;

    public CityService() {
        this.cities = new ConcurrentHashMap<>();
    }

    public City addCity(City city) {
        cities.put(city.getId(), city);
        return city;
    }

    public Map<String, City> getAllCities() {
        return cities;
    }

    public City getCityById(String cityId) {
        if (cities.containsKey(cityId)) {
            return cities.get(cityId);
        }

        throw new ResourceNotFoundException("City not found with id: " + cityId);
    }

    public void deleteCityById(String cityId) {
        City city = cities.remove(cityId);

        if (city == null) {
            throw new ResourceNotFoundException("City not found with id: " + cityId);
        }
    }

}
