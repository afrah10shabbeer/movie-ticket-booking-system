package com.afrah.movie_ticket_booking_system.DataTransferObjects;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class CreateUserRequest {

    @NotBlank(message = "Name is required")
    @Schema(example = "John Reese")
    private String name;

    @NotBlank(message = "Email is required")
    @Schema(example = "johnreese@poi.com")
    @Email(message = "Please enter a valid email address")
    private String email;

    public CreateUserRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}