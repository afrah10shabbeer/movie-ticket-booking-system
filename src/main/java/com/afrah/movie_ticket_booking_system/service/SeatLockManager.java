package com.afrah.movie_ticket_booking_system.service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.afrah.movie_ticket_booking_system.entity.Seat;
import com.afrah.movie_ticket_booking_system.entity.Show;
import com.afrah.movie_ticket_booking_system.enums.SeatStatus;
import com.afrah.movie_ticket_booking_system.exception.SeatNotAvailableException;

import jakarta.annotation.PreDestroy;

/**
 * Manages temporary seat locking for a show.
 * Seats are automatically unlocked if booking is not completed.
 */
@Service
public class SeatLockManager {

    private static final Logger logger = LoggerFactory.getLogger(SeatLockManager.class);

    /*
     * Show -> (SeatId -> UserId)
     * 
     * Example:
     * 
     * Show A
     * |
     * |-- A1 -> user123
     * |-- A2 -> user123
     */
    private final Map<Show, Map<String, String>> lockedSeats = new ConcurrentHashMap<>();

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    // Demo timeout. Real systems use 5-10 minutes.
    private static final long LOCK_TIMEOUT_MS = 30000;

    /**
     * Temporarily locks seats for a user.
     */
    public void lockSeats(Show show, List<Seat> seats, String userId) {

        synchronized (show) {

            // 1. Check availability
            for (Seat seat : seats) {

                if (seat.getStatus() != SeatStatus.AVAILABLE) {

                    throw new SeatNotAvailableException("Seat already unavailable: " + seat.getId());
                }
            }

            // 2. Mark seats as locked
            for (Seat seat : seats) {
                seat.setStatus(SeatStatus.LOCKED);
            }

            // 3. Create lock map for show if absent
            lockedSeats.putIfAbsent(show, new ConcurrentHashMap<>());
            Map<String, String> showLocks = lockedSeats.get(show);

            // 4. Store ownership
            for (Seat seat : seats) {

                showLocks.put(seat.getId(), userId);
            }

            // 5. Schedule auto unlock
            scheduler.schedule(
                    () -> unlockSeats(
                            show,
                            seats,
                            userId),
                    LOCK_TIMEOUT_MS,
                    TimeUnit.MILLISECONDS);

            logger.info(
                    "Seats {} locked for user {}",
                    seats.stream()
                            .map(Seat::getId)
                            .toList(),
                    userId);
        }
    }

    /**
     * Unlock seats after timeout
     * or after successful booking.
     */
    public void unlockSeats(Show show, List<Seat> seats, String userId) {
        synchronized (show) {

            Map<String, String> showLocks = lockedSeats.get(show);

            if (showLocks == null) {
                return;
            }

            for (Seat seat : seats) {

                String lockedUser = showLocks.get(seat.getId());

                // Only unlock if same user owns the lock
                if (userId.equals(lockedUser)) {

                    showLocks.remove(seat.getId());

                    // Timeout case
                    if (seat.getStatus() == SeatStatus.LOCKED) {

                        seat.setStatus(SeatStatus.AVAILABLE);

                        logger.info(
                                "Seat {} released due to timeout",
                                seat.getId());
                    }
                }
            }

            // Remove empty show lock entry
            if (showLocks.isEmpty()) {

                lockedSeats.remove(show);
            }
        }
    }

    /**
     * Spring automatically calls this
     * when application shuts down.
     */
    @PreDestroy
    public void shutdown() {

        logger.info("Shutting down SeatLockManager scheduler");

        scheduler.shutdown();

        try {
            if (!scheduler.awaitTermination(5, TimeUnit.SECONDS)) {
                scheduler.shutdownNow();
            }

        } catch (InterruptedException e) {

            scheduler.shutdownNow();
            Thread.currentThread()
                    .interrupt();
        }
    }
}