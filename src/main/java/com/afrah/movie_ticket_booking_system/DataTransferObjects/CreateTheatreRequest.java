package com.afrah.movie_ticket_booking_system.DataTransferObjects;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

@Schema(example = """
    {
      "name": "PVR Nexus Mall",
      "cityId": "6be0e3cf-2c99-4c15-bb2d-7b5f03723473",
      "screens": [
        {
          "seats": [
            {
              "seatNumber": "A1",
              "seatRow": 1,
              "seatColumn": 1,
              "seatType": "REGULAR"
            },
            {
              "seatNumber": "A2",
              "seatRow": 1,
              "seatColumn": 2,
              "seatType": "REGULAR"
            },
            {
              "seatNumber": "B1",
              "seatRow": 2,
              "seatColumn": 1,
              "seatType": "PREMIUM"
            }
          ]
        },
        {
          "seats": [
            {
              "seatNumber": "A1",
              "seatRow": 1,
              "seatColumn": 1,
              "seatType": "REGULAR"
            },
            {
              "seatNumber": "C1",
              "seatRow": 3,
              "seatColumn": 1,
              "seatType": "RECLINER"
            }
          ]
        }
      ]
    }
    """)
public class CreateTheatreRequest {

  @NotBlank(message = "Theatre name is required")
  @Schema(example = "PVR Nexus Mall")
  private String name;

  @NotBlank(message = "City ID is required")
  @Schema(example = "6be0e3cf-2c99-4c15-bb2d-7b5f03723473")
  private String cityId;

  @Valid
  @NotEmpty(message = "At least one screen is required")
  @Schema(description = "List of screens in the theatre")
  private List<CreateScreenRequest> screens;

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

  public List<CreateScreenRequest> getScreens() {
    return screens;
  }

  public void setScreens(List<CreateScreenRequest> screens) {
    this.screens = screens;
  }
}