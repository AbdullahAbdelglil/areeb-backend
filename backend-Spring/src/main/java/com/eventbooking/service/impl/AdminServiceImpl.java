package com.eventbooking.service.impl;

import com.eventbooking.service.AdminService;
import com.eventbooking.service.BookingService;
import com.eventbooking.service.dto.AdminViewEventDTO;
import com.eventbooking.service.dto.EventDTO;
import com.eventbooking.service.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    private final BookingService bookingService;

    public AdminServiceImpl(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @Override
    public List<AdminViewEventDTO> getAdminViewEvents(List<EventDTO> events) {
        return events.stream().map(event -> {
            AdminViewEventDTO eventDTO = new AdminViewEventDTO(event);

            // Add number of bookings
            long numberOfBookings = bookingService.getBookingsCountByEventId(event.getId());
            eventDTO.setNumberOfBookings(numberOfBookings);

            return eventDTO;
        }).toList();
    }

    @Override
    public AdminViewEventDTO getAdminViewDetailEvents(EventDTO event) {
        AdminViewEventDTO eventDetailDTO = new AdminViewEventDTO(event);
        long numberOfBookings = bookingService.getBookingsCountByEventId(event.getId());
        eventDetailDTO.setNumberOfBookings(numberOfBookings);

        List<UserDTO> users = bookingService.getUsersByEventId(event.getId());
        eventDetailDTO.setUsers(users);

        return eventDetailDTO;
    }
}

