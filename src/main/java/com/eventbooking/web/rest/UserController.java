package com.eventbooking.web.rest;

import com.eventbooking.service.EventService;
import com.eventbooking.service.UserBookingService;
import com.eventbooking.service.dto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;

import reactor.core.publisher.Sinks;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private final Logger log = LoggerFactory.getLogger(UserController.class);

    private final UserBookingService userBookingService;

    private final EventService eventService;

    private final Sinks.Many<String> sink = Sinks.many().multicast().onBackpressureBuffer();

    public UserController(UserBookingService userBookingService,
                          EventService eventService) {
        this.userBookingService = userBookingService;
        this.eventService = eventService;
    }

    @GetMapping("/homepage")
    public ResponseEntity<List<HomePageEventDTO>> getHomePageEvents(Pageable pageable,
                                                                    @RequestParam(required = false) Integer categoryId) throws IOException {
        log.debug("REST request to get the home page");
        Page<HomePageEventDTO> page;
        page = eventService.getHomePageEvents(pageable, categoryId);
        return ResponseEntity.ok().body(page.getContent());
    }

    @GetMapping("/event/{eventId}")
    public ResponseEntity<UserViewEventDetailsDTO> getEventDetails(@PathVariable Long eventId) {
        log.debug("REST request to get event details by id {}", eventId);
        UserViewEventDetailsDTO eventDetails = eventService.getEventDetails(eventId);
        return ResponseEntity.ok().body(eventDetails);
    }

    @PostMapping("/book-event/{eventId}")
    public ResponseEntity<BookingResponseDTO> bookEvent(@PathVariable Long eventId) throws URISyntaxException {
        log.debug("REST request to book event : {}", eventId);
        BookingResponseDTO response = userBookingService.bookEvent(eventId);
        return ResponseEntity.created(new URI("/user/bookings/"+response.getBookingId())).body(response);
    }

    @GetMapping("/bookings")
    public ResponseEntity<List<UserViewBookingDTO>> getBookings(Pageable pageable) {
        log.debug("REST request to get bookings");
        List<UserViewBookingDTO> userBookings = userBookingService.getUserBookings(pageable);
        return ResponseEntity.ok().body(userBookings);
    }

    @DeleteMapping("/cancel-booking/{bookingId}")
    public ResponseEntity<Void> cancelBooking(@PathVariable("bookingId") Long bookingId) {
        log.debug("REST request to cancel Booking : {}", bookingId);
        userBookingService.cancelBooking(bookingId);
        return ResponseEntity.noContent().build();
    }

}
