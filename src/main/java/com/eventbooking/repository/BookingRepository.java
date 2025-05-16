package com.eventbooking.repository;

import com.eventbooking.domain.Booking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    Page<Booking> findByUserEmailOrderByBookingDateDesc(String userEmail, Pageable pageable);
    void deleteByIdAndUserEmail(Long id, String userEmail);

    boolean existsByUserEmailAndEventId(String email, Long eventId);

    Integer countByEventId(Long eventId);

    List<Booking> findByEventId(Long eventId);
}
