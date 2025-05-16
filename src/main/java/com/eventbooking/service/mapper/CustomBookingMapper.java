package com.eventbooking.service.mapper;

import com.eventbooking.service.EventService;
import com.eventbooking.service.UserService;
import com.eventbooking.service.dto.UserDTO;
import com.eventbooking.service.dto.UserViewEventDetailsDTO;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Component
public class CustomBookingMapper {
    private final UserService userService;
    private final EventService eventService;


    public CustomBookingMapper(UserService userService,
                               EventService eventService) {
        this.userService = userService;
        this.eventService = eventService;
    }

    @Named("emailToUserDTO")
    public UserDTO mapEmailToUserDTO(String email) {
        return userService.findOne(email).orElseThrow();
    }

    @Named("idToEventDetailsDTO")
    public UserViewEventDetailsDTO mapIdToEventDetailsDTO(Long eventId) {
        return new UserViewEventDetailsDTO(eventService.findOne(eventId).orElseThrow());
    }
}
