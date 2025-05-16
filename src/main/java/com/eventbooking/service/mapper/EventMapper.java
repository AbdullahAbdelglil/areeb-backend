package com.eventbooking.service.mapper;


import com.eventbooking.domain.Event;
import com.eventbooking.service.dto.EventDTO;
import com.eventbooking.service.dto.HomePageEventDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Event} and its DTO {@link EventDTO}.
 */
@Mapper(componentModel = "spring")
public interface EventMapper extends EntityMapper<EventDTO, Event> {
    HomePageEventDTO toHomePageEventDTO(Event event);
}
