package com.eventbooking.service;

import com.eventbooking.service.dto.AdminViewEventDTO;
import com.eventbooking.service.dto.EventDTO;

import java.util.List;

public interface AdminService {

    List<AdminViewEventDTO> getAdminViewEvents(List<EventDTO> events);

    AdminViewEventDTO getAdminViewDetailEvents(EventDTO events);
}
