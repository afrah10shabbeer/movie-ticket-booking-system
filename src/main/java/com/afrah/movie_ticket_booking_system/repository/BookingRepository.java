package com.afrah.movie_ticket_booking_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.afrah.movie_ticket_booking_system.entity.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, String> {

}
