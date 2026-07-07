package com.afrah.movie_ticket_booking_system.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.afrah.movie_ticket_booking_system.DataTransferObjects.CreateShowRequest;
import com.afrah.movie_ticket_booking_system.entity.Show;
import com.afrah.movie_ticket_booking_system.service.ShowService;
import com.afrah.movie_ticket_booking_system.strategy.Pricing.WeekdayPricingStrategy;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/shows")
public class ShowController {

    private final ShowService showService;

    public ShowController(ShowService showService) {
        this.showService = showService;
    }

    @Operation(summary = "Add a show", description = "Creates a new movie show.")
    @PostMapping
    public ResponseEntity<Show> addShow(@RequestBody CreateShowRequest request) {

        Show show = showService.addShow(
                request.getMovieId(),
                request.getScreenId(),
                request.getStartTime(),
                new WeekdayPricingStrategy());

        return ResponseEntity.status(HttpStatus.CREATED).body(show);
    }

    @Operation(summary = "Get show by ID", description = "Retrieves a show using its unique ID.")
    @GetMapping("/{showId}")
    public ResponseEntity<Show> getShowById(@PathVariable String showId) {
        return ResponseEntity.ok(showService.getShowById(showId));
    }

    @Operation(summary = "Get all shows", description = "Retrieves all available shows.")
    @GetMapping
    public ResponseEntity<List<Show>> getAllShows() {
        return ResponseEntity.ok(showService.getAllShows());
    }

    @Operation(summary = "Delete a show", description = "Deletes a show using its unique ID.")
    @DeleteMapping("/{showId}")
    public ResponseEntity<String> deleteShowById(@PathVariable String showId) {
        showService.deleteShowById(showId);
        return ResponseEntity.ok("Show deleted successfully.");
    }

    @Operation(summary = "Search shows", description = "Finds shows by movie title and city.")
    @GetMapping("/search")
    public ResponseEntity<List<Show>> findShows(@RequestParam String movieTitle, @RequestParam String cityName) {
        return ResponseEntity.ok(showService.findShows(movieTitle, cityName));
    }
}