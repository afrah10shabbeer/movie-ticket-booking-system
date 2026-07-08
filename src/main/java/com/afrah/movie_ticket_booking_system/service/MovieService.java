package com.afrah.movie_ticket_booking_system.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.afrah.movie_ticket_booking_system.entity.*;
import com.afrah.movie_ticket_booking_system.exception.ResourceNotFoundException;
import com.afrah.movie_ticket_booking_system.repository.MovieRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class MovieService {
    private MovieRepository movieRepository;

    private static final Logger logger = LoggerFactory.getLogger(MovieService.class);

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Movie addMovie(Movie movie) {

        logger.info("Adding movie '{}'.", movie.getTitle());

        Movie savedMovie = movieRepository.save(movie);

        logger.info("Movie '{}' added successfully with ID '{}'.",
                movie.getTitle(), movie.getMovieId());

        return savedMovie;
    }

    public List<Movie> getAllMovies() {

        logger.info("Fetching all movies.");

        return movieRepository.findAll();
    }

    public Movie getMovieById(String movieId) {

        logger.info("Fetching movie with ID '{}'.", movieId);

        Optional<Movie> movie = movieRepository.findById(movieId);

        if (movie.isPresent()) {
            logger.info("Movie '{}' fetched successfully.", movie.get().getTitle());
            return movie.get();
        }

        logger.warn("Movie not found with ID '{}'.", movieId);

        throw new ResourceNotFoundException("Movie not found with id: " + movieId);
    }

    public void deleteMovieById(String movieId) {

        logger.info("Deleting movie with ID '{}'.", movieId);

        Movie movie = getMovieById(movieId);

        movieRepository.delete(movie);

        logger.info("Movie '{}' deleted successfully.", movie.getTitle());
    }

}
