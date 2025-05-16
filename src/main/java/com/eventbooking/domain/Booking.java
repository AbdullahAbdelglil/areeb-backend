package com.eventbooking.domain;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "event_id")
    private Long eventId;

    @Column(name = "booking_date")
    private Date bookingDate;

    public Booking() {
    }

    public Booking(Long id,
                   String userEmail,
                   Long eventId) {
        this.id = id;
        this.userEmail = userEmail;
        this.eventId = eventId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    @PrePersist
    public void setBookingDate() {
        this.bookingDate = new Date(System.currentTimeMillis());
    }
}
