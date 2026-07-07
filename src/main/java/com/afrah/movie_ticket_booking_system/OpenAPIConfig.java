package com.afrah.movie_ticket_booking_system;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI movieTicketBookingOpenAPI() {

        return new OpenAPI()
                .info(new Info()
                        .title("Movie Ticket Booking System API")
                        .description("REST API for managing movies, theatres, shows, users, bookings, and payments.")
                        .version("1.0.0"));
    }
}