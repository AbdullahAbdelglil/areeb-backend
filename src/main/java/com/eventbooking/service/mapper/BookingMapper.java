package com.eventbooking.service.mapper;

import com.eventbooking.domain.Booking;
import com.eventbooking.service.dto.BookingDTO;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",
        uses = {UserMapper.class, EventMapper.class, CustomBookingMapper.class})
public interface BookingMapper extends EntityMapper<BookingDTO, Booking> {

    @Override
    @Mapping(source = "userEmail", target = "user", qualifiedByName = "emailToUserDTO")
    @Mapping(source = "eventId", target = "event", qualifiedByName = "idToEventDetailsDTO")
    BookingDTO toDto(Booking booking);

    @Override
    @Mapping(source = "user.email", target = "userEmail")
    @Mapping(source = "event.id", target = "eventId")
    Booking toEntity(BookingDTO dto);
}
