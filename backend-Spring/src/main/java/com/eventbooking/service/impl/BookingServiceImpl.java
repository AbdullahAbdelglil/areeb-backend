package com.eventbooking.service.impl;

import com.eventbooking.domain.Booking;
import com.eventbooking.repository.BookingRepository;
import com.eventbooking.service.BookingService;
import com.eventbooking.service.dto.BookingDTO;
import com.eventbooking.service.dto.EventDTO;
import com.eventbooking.service.dto.UserDTO;
import com.eventbooking.service.dto.UserViewEventDetailsDTO;
import com.eventbooking.service.mapper.BookingMapper;

import com.eventbooking.service.mapper.CustomBookingMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    private final Logger log = LoggerFactory.getLogger(BookingServiceImpl.class);

    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;
    private final CustomBookingMapper customBookingMapper;

    public BookingServiceImpl(BookingRepository bookingRepository,
                              BookingMapper bookingMapper,
                              CustomBookingMapper customBookingMapper) {
        this.bookingRepository = bookingRepository;
        this.bookingMapper = bookingMapper;
        this.customBookingMapper = customBookingMapper;
    }

    @Override
    @Transactional
    public BookingDTO save(BookingDTO bookingDTO) {
        log.debug("Request to save Booking : {}", bookingDTO);
        Booking booking = bookingMapper.toEntity(bookingDTO);

        booking = bookingRepository.save(booking);

        UserDTO userDTO = customBookingMapper.mapEmailToUserDTO(booking.getUserEmail());
        UserViewEventDetailsDTO eventDetailsDTO = customBookingMapper.mapIdToEventDetailsDTO(booking.getEventId());
        return new BookingDTO(booking.getId(), userDTO, eventDetailsDTO, booking.getBookingDate());
    }

    @Override
    @Transactional(readOnly = true)
    public Page<BookingDTO> getBookingsByUserEmail(String userEmail, Pageable pageable) {
        return bookingRepository.findByUserEmailOrderByBookingDateDesc(userEmail, pageable).map(bookingMapper::toDto);
    }

    @Override
    public boolean existsByUserEmailAndEventId(String email, Long eventId) {
        return bookingRepository.existsByUserEmailAndEventId(email, eventId);
    }

    @Override
    @Transactional
    public void deleteByIdAndUserEmail(Long bookingId, String userEmail) {
        log.debug("Request to delete booking : {}", bookingId);
        bookingRepository.deleteByIdAndUserEmail(bookingId, userEmail);
    }

    /**
     * Get the number of bookings for a specific event
     * @param eventId the event ID
     * @return the count of bookings
     */
    @Override
    public Integer getBookingsCountByEventId(Long eventId) {
        return bookingRepository.countByEventId(eventId);
    }

    /**
     * Get all users who booked a specific event
     * @param eventId the event ID
     * @return list of UserDTO objects
     */
    public List<UserDTO> getUsersByEventId(Long eventId) {
        List<BookingDTO> bookings = bookingRepository.findByEventId(eventId).stream()
                .map(bookingMapper::toDto)
                .toList();

        return bookings.stream()
                .map(BookingDTO::getUser)
                .toList();
    }

}
