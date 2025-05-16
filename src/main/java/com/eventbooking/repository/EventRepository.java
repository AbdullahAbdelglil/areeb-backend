package com.eventbooking.repository;

import com.eventbooking.domain.Event;

import com.eventbooking.service.dto.HomePageEventDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data JPA repository for the Event entity.
 */
@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    @Query("SELECT new com.eventbooking.service.dto.HomePageEventDTO(e.id, e.name, e.description, e.categoryId, e.imageUrl, e.date, e.price, false) FROM Event e order by e.createdAt desc")
    Page<HomePageEventDTO> findAllHomePageEvents(Pageable pageable);

    Page<Event> findAllByOrderByCreatedAtDesc(Pageable pageable);
}

