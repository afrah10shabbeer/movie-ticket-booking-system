package com.afrah.movie_ticket_booking_system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.afrah.movie_ticket_booking_system.entity.Show;

public interface ShowRepository extends JpaRepository<Show, String> {

    List<Show> findByMovie_TitleIgnoreCase(String title);

}