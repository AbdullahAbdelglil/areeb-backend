package com.eventbooking.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AdminViewEventDTO extends EventDTO {
    private List<UserDTO> users;

    private Long numberOfBookings;

    public AdminViewEventDTO(EventDTO eventDTO) {
        super(eventDTO.getId(),
                eventDTO.getName(),
                eventDTO.getDescription(),
                eventDTO.getAgenda(),
                eventDTO.getCategory(),
                eventDTO.getDate(),
                eventDTO.getVenue(),
                eventDTO.getPrice(),
                eventDTO.getImageUrl());
    }

    public List<UserDTO> getUsers() {
        return users;
    }

    public void setUsers(List<UserDTO> users) {
        this.users = users;
    }

    public Long getNumberOfBookings() {
        return numberOfBookings;
    }

    public void setNumberOfBookings(Long numberOfBookings) {
        this.numberOfBookings = numberOfBookings;
    }
}
