package com.eventbooking.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserViewBookingDTO extends BookingDTO {

    public UserViewBookingDTO(BookingDTO booking) {
        super(booking.getId(), booking.getEvent(), booking.getBookingDate());
    }

    @Override
    public UserDTO getUser() {
        return null;
    }
}
