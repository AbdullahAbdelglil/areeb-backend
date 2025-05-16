package com.eventbooking.web.rest;

import com.eventbooking.service.AdminService;
import com.eventbooking.service.EventService;
import com.eventbooking.service.ImageStorageService;
import com.eventbooking.service.dto.AdminViewEventDTO;
import com.eventbooking.service.dto.EventDTO;
import com.eventbooking.web.errors.BadRequestAlertException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

    private final EventService eventService;
    private final AdminService adminService;
    private final ImageStorageService imageStorageService;
    private final Sinks.Many<String> sink = Sinks.many().multicast().onBackpressureBuffer();

    public AdminController(EventService eventService,
                           AdminService adminService,
                           ImageStorageService imageStorageService) {
        this.eventService = eventService;
        this.adminService = adminService;
        this.imageStorageService = imageStorageService;
    }

    @GetMapping("/dashboard")
    public ResponseEntity<List<AdminViewEventDTO>> getDashboard(Pageable pageable) {
        Page<EventDTO> events = eventService.findAll(pageable);
        List<AdminViewEventDTO> eventDTOs = adminService.getAdminViewEvents(events.getContent());
        return ResponseEntity.ok(eventDTOs);
    }

    // Get event by ID
    @GetMapping("/event/{id}")
    public ResponseEntity<AdminViewEventDTO> getEventById(@PathVariable Long id) {
        Optional<EventDTO> event = eventService.findOne(id);
        if (event.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        EventDTO eventDTO = event.get();
        return ResponseEntity.ok(adminService.getAdminViewDetailEvents(eventDTO));
    }

    // Create new event with image
    @PostMapping("/control-panel")
    public ResponseEntity<EventDTO> createEvent(@RequestPart("eventDTO") EventDTO eventDTO,
                                                @RequestPart(value = "file", required = false) MultipartFile image,
                                                HttpServletRequest request) {
        EventDTO savedEvent = eventService.save(eventDTO);
        if (savedEvent != null && image!=null) {
            String imageUrl = addImageToEvent(image, request, savedEvent.getId());
            savedEvent.setImageUrl(imageUrl);
        }
        sink.tryEmitNext("{\"type\":\"eventCreated\", \"eventId\":\"" + eventDTO.getId() + "\"}");
        return new ResponseEntity<>(savedEvent, HttpStatus.CREATED);
    }

    // Update event with image
    @PutMapping("/control-panel/{id}")
    public ResponseEntity<EventDTO> updateEvent(@PathVariable Long id,
                                                @RequestPart("eventDTO") EventDTO eventDTO,
                                                @RequestPart(value = "file", required = false) MultipartFile image,
                                                HttpServletRequest request) throws BadRequestAlertException {
        EventDTO updatedEvent = eventService.update(id, eventDTO);
        if(updatedEvent!=null && image!=null) {
            String imageUrl = addImageToEvent(image, request, id);
            updatedEvent.setImageUrl(imageUrl);
        }
        sink.tryEmitNext("{\"type\":\"eventUpdated\", \"eventId\":\"" + eventDTO.getId() + "\"}");
        return ResponseEntity.ok(updatedEvent);
    }

    // Delete event
    @DeleteMapping("/control-panel/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        // Check if event exists
        if (!eventService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        eventService.delete(id);
        sink.tryEmitNext("{\"type\":\"eventDeleted\", \"eventId\":\"" + id + "\"}");
        return ResponseEntity.noContent().build();
    }

    // upload image
    @PostMapping("/events/{eventId}/upload-image")
    public ResponseEntity<String> uploadEventImage(@PathVariable Long eventId,
                                                   @RequestParam(name = "file") MultipartFile image,
                                                   HttpServletRequest request) {
        String imageUrl = addImageToEvent(image, request, eventId);
        return ResponseEntity.ok("Image uploaded and linked successfully: " + imageUrl);
    }

    private String addImageToEvent(MultipartFile image, HttpServletRequest request, Long savedEvent) {
        String fileName = imageStorageService.storeImage(image);
        String baseUrl = request.getRequestURL().toString().replace(request.getRequestURI(), "");
        String imageUrl = baseUrl + "/api/v1/uploads/" + fileName;

        eventService.addImageToEvent(savedEvent, imageUrl);
        return imageUrl;
    }

    @GetMapping(value = "/events-updates",  produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> streamEvents() {
        return sink.asFlux();
    }
}
