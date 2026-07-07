package com.afrah.movie_ticket_booking_system.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.afrah.movie_ticket_booking_system.DataTransferObjects.CreateTheatreRequest;
import com.afrah.movie_ticket_booking_system.entity.Theatre;
import com.afrah.movie_ticket_booking_system.service.TheatreService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/theatres")
public class TheatreController {

    private final TheatreService theatreService;

    public TheatreController(TheatreService theatreService) {
        this.theatreService = theatreService;
    }

    @Operation(summary = "Add a theatre", description = "Creates a new theatre.")
    @PostMapping
    public ResponseEntity<Theatre> addTheatre(@Valid @RequestBody CreateTheatreRequest request) {

        Theatre theatre = theatreService.addTheatre(
                request.getName(),
                request.getCityId(),
                request.getScreens());

        return ResponseEntity.status(HttpStatus.CREATED).body(theatre);
    }

    @Operation(summary = "Get all theatres", description = "Retrieves all available theatres.")
    @GetMapping
    public ResponseEntity<List<Theatre>> getAllTheatres() {
        return ResponseEntity.ok(theatreService.getAllTheatres());
    }

    @Operation(summary = "Get theatre by ID", description = "Retrieves a theatre using its unique ID.")
    @GetMapping("/{theatreId}")
    public ResponseEntity<Theatre> getTheatreById(@PathVariable String theatreId) {
        return ResponseEntity.ok(theatreService.getTheatreById(theatreId));
    }

    @Operation(summary = "Delete a theatre", description = "Deletes a theatre using its unique ID.")
    @DeleteMapping("/{theatreId}")
    public ResponseEntity<String> deleteTheatreById(@PathVariable String theatreId) {
        theatreService.deleteTheatreById(theatreId);
        return ResponseEntity.ok("Theatre deleted successfully.");
    }
}