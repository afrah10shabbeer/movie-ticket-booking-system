package com.afrah.movie_ticket_booking_system.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.afrah.movie_ticket_booking_system.DataTransferObjects.CreateMovieRequest;
import com.afrah.movie_ticket_booking_system.entity.Movie;
import com.afrah.movie_ticket_booking_system.service.MovieService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieService service;

    public MovieController(MovieService service) {
        this.service = service;
    }

    @Operation(summary = "Add a movie", description = "Creates a new movie.")
    @PostMapping
    public ResponseEntity<Movie> addMovie(@Valid @RequestBody CreateMovieRequest request) {

        Movie movie = new Movie(
                request.getTitle(),
                request.getDurationInMinutes(),
                request.getGenre(),
                request.getLanguage());

        return ResponseEntity.status(HttpStatus.CREATED).body(service.addMovie(movie));
    }

    @Operation(summary = "Get all movies", description = "Retrieves all available movies.")

    @GetMapping
    public ResponseEntity<List<Movie>> getAllMovies() {
        return ResponseEntity.ok(service.getAllMovies());
    }

    @Operation(summary = "Get movie by ID", description = "Retrieves a movie using its unique ID.")
    @GetMapping("/{movieId}")
    public ResponseEntity<Movie> getMovieById(@PathVariable String movieId) {
        return ResponseEntity.ok(service.getMovieById(movieId));
    }

    @Operation(summary = "Delete a movie", description = "Deletes a movie using its unique ID.")
    @DeleteMapping("/{movieId}")
    public ResponseEntity<String> deleteMovieById(@PathVariable String movieId) {
        service.deleteMovieById(movieId);
        return ResponseEntity.ok("Movie deleted successfully.");
    }
}