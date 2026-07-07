package com.afrah.movie_ticket_booking_system.DataTransferObjects;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class CreateMovieRequest {

    @NotBlank(message = "Movie title is required")
    @Schema(example = "Inception")
    private String title;

    @NotBlank(message = "Genre is required")
    @Schema(example = "Science fiction")
    private String genre;

    @NotBlank(message = "Language is required")
    @Schema(example = "English")
    private String language;

    @Min(value = 1, message = "Duration must be greater than 0 minutes")
    @Schema(example = "127")
    private int durationInMinutes;

    public CreateMovieRequest() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getDurationInMinutes() {
        return durationInMinutes;
    }

    public void setDurationInMinutes(int durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }
}
