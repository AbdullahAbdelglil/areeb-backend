package com.eventbooking.service;

import com.eventbooking.service.dto.BookingDTO;
import com.eventbooking.service.dto.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookingService {

    //Save booking
    BookingDTO save(BookingDTO bookingDTO);

    Page<BookingDTO> getBookingsByUserEmail(String userEmail, Pageable pageable);

    // For user only: User can delete his booking
    void deleteByIdAndUserEmail(Long bookingId, String userEmail);

    Integer getBookingsCountByEventId(Long eventId);

    boolean existsByUserEmailAndEventId(String email, Long eventId);

    List<UserDTO> getUsersByEventId(Long id);
}
