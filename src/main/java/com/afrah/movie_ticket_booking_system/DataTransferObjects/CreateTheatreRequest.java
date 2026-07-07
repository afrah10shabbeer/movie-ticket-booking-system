package com.afrah.movie_ticket_booking_system.DataTransferObjects;

import java.util.List;

import com.afrah.movie_ticket_booking_system.entity.Screen;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public class CreateTheatreRequest {

    @NotBlank(message = "Theatre name is required")
    @Schema(example = "PVR Inox")
    private String name;

    @NotBlank(message = "City ID is required")
    @Schema(example = "<cityId>")
    private String cityId;

    @NotEmpty(message = "At least one screen is required")
    @Schema(description = "List of screens in the theatre", example = """
            [
              {
                "id": "SCREEN1",
                "seats": [
                  {
                    "id": "<screenId>",
                    "row": 1,
                    "column": 1,
                    "seatType": "REGULAR"
                  },
                  {
                    "id": "<screenId>",
                    "row": 1,
                    "column": 2,
                    "seatType": "PREMIUM"
                  }
                ]
              }
            ]
            """)
    private List<Screen> screens;

    public CreateTheatreRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public List<Screen> getScreens() {
        return screens;
    }

    public void setScreens(List<Screen> screens) {
        this.screens = screens;
    }
}