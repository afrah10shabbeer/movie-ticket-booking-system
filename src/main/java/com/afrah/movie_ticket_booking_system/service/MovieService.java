package com.afrah.movie_ticket_booking_system.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import com.afrah.movie_ticket_booking_system.entity.*;
import com.afrah.movie_ticket_booking_system.exception.ResourceNotFoundException;

@Service
public class MovieService {
    private final Map<String, Movie> movies;

    private MovieService() {
        this.movies = new ConcurrentHashMap<>();
    }

    public Movie addMovie(Movie movie) {
        movies.put(movie.getId(), movie);
        return movie;
    }

    public Map<String, Movie> getAllMovies() {
        return movies;
    }

    public Movie getMovieById(String movieId) {
        if (movies.containsKey(movieId)) {
            return movies.get(movieId);
        }
        throw new ResourceNotFoundException("Movie not found with id: " + movieId);
    }

    public void deleteMovieById(String movieId) {
        if (movies.containsKey(movieId)) {
            movies.remove(movieId);
        }
        throw new ResourceNotFoundException("Movie not found with id: " + movieId);
    }

}
