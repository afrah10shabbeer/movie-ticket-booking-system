package com.afrah.movie_ticket_booking_system.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.afrah.movie_ticket_booking_system.DataTransferObjects.CreateCityRequest;
import com.afrah.movie_ticket_booking_system.entity.City;
import com.afrah.movie_ticket_booking_system.service.CityService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/cities")
public class CityController {

    private final CityService service;

    public CityController(CityService service) {
        this.service = service;
    }

    @Operation(summary = "Add a city", description = "Creates a new city.")
    @PostMapping
    public ResponseEntity<City> addCity(@Valid @RequestBody CreateCityRequest request) {

        City city = new City(
                request.getName(),
                request.getState());

        City savedCity = service.addCity(city);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedCity);
    }

    @Operation(summary = "Get all cities", description = "Retrieves all available cities.")
    @GetMapping
    public ResponseEntity<List<City>> getAllCities() {
        return ResponseEntity.ok(service.getAllCities());
    }

    @Operation(summary = "Get city by ID", description = "Retrieves a city using its unique ID.")
    @GetMapping("/{cityId}")
    public ResponseEntity<City> getCityById(@PathVariable String cityId) {
        return ResponseEntity.ok(service.getCityById(cityId));
    }

    @Operation(summary = "Delete a city", description = "Deletes a city using its unique ID.")
    @DeleteMapping("/{cityId}")
    public ResponseEntity<String> deleteCityById(@PathVariable String cityId) {
        service.deleteCityById(cityId);
        return ResponseEntity.ok("City deleted successfully.");
    }
}