package com.eventbooking.service;

import com.eventbooking.service.dto.BookingDTO;
import com.eventbooking.service.dto.BookingResponseDTO;
import com.eventbooking.service.dto.UserViewBookingDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserBookingService {

    BookingResponseDTO bookEvent(Long eventId);

    List<UserViewBookingDTO> getUserBookings(Pageable pageable);

    void cancelBooking(Long bookingId);

}
