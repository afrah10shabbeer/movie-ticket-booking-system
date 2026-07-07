package com.afrah.movie_ticket_booking_system.DataTransferObjects;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Request payload for creating a city")
public class CreateCityRequest {

    @NotBlank(message = "City name cannot be blank")
    @Schema(example = "Hyderabad")
    private String name;

    @NotBlank(message = "State cannot be blank")
    @Schema(example = "Telangana")
    private String state;

    public CreateCityRequest() {
    }

    public CreateCityRequest(String name, String state) {
        this.name = name;
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
