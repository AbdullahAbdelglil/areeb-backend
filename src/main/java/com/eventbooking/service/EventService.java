package com.eventbooking.service;

import java.io.IOException;
import java.util.Optional;

import com.eventbooking.domain.Category;
import com.eventbooking.service.dto.EventDTO;
import com.eventbooking.service.dto.UserViewEventDetailsDTO;
import com.eventbooking.service.dto.HomePageEventDTO;
import com.eventbooking.web.errors.BadRequestAlertException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.eventbooking.domain.Event}.
 */
public interface EventService {

    EventDTO save(EventDTO eventDTO);

    EventDTO update(Long id, EventDTO eventDTO) throws BadRequestAlertException;

    Optional<EventDTO> partialUpdate(EventDTO eventDTO);

    Page<EventDTO> findAll(Pageable pageable);

    Page<HomePageEventDTO> getHomePageEvents(Pageable pageable, Integer categoryId) throws IOException;
    
    Optional<EventDTO> findOne(Long id);

    UserViewEventDetailsDTO getEventDetails(Long id);

    boolean existsById(Long id);

    void delete(Long id);

    void addImageToEvent(Long eventId, String imageUrl);
}
