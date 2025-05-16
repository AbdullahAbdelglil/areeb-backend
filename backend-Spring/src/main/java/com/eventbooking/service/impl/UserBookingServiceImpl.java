package com.eventbooking.service.impl;

import com.eventbooking.domain.User;
import com.eventbooking.service.*;
import com.eventbooking.service.dto.*;
import com.eventbooking.util.HomePageUtil;
import com.eventbooking.util.SecurityUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class UserBookingServiceImpl implements UserBookingService {

    private final BookingService bookingService;

    private final EventService eventService;

    public UserBookingServiceImpl(BookingService bookingService,
                                  EventService eventService) {
        this.bookingService = bookingService;
        this.eventService = eventService;
    }

    @Override
    public BookingResponseDTO bookEvent(Long eventId) {
        UserDTO currentUser = SecurityUtil.getCurrentUser();
        if(currentUser == null) {
            return null;
        }
        EventDTO event = eventService.findOne(eventId).orElseThrow();
        UserViewEventDetailsDTO eventDetailsDTO = new UserViewEventDetailsDTO(event);
        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setEvent(eventDetailsDTO);
        bookingDTO.setUser(currentUser);

        BookingDTO savedBooking =  bookingService.save(bookingDTO);
        return new BookingResponseDTO(savedBooking);
    }

    @Override
    public List<UserViewBookingDTO> getUserBookings(Pageable pageable) {
        UserDTO currentUser = SecurityUtil.getCurrentUser();
        if(currentUser == null) {
            return null;
        }
        String userEmail = currentUser.getEmail();
        Page<BookingDTO> bookingDTOPage = bookingService.getBookingsByUserEmail(userEmail, pageable);
        List<UserViewBookingDTO> userViewBookingDTOList = new ArrayList<>();
        for(var bookingDTO : bookingDTOPage.getContent()) {
            userViewBookingDTOList.add(new UserViewBookingDTO(bookingDTO));
        }
        return userViewBookingDTOList;
    }

    @Override
    public void cancelBooking(Long bookingId) {
        UserDTO currentUser = SecurityUtil.getCurrentUser();
        if(currentUser == null) {
            return;
        }
        String userEmail = currentUser.getEmail();
        bookingService.deleteByIdAndUserEmail(bookingId, userEmail);
    }
}
