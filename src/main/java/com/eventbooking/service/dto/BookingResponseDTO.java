package com.eventbooking.service.dto;

import java.util.Date;

public class BookingResponseDTO {
    private Long bookingId;
    private String message;

    public BookingResponseDTO() {
    }

    public BookingResponseDTO(Long bookingId, String message, Date bookingDate) {
        this.bookingId = bookingId;
        this.message = message;
    }

    public BookingResponseDTO(BookingDTO bookingDTO) {
        this.bookingId = bookingDTO.getId();
        this.message = generateBookingMessage(bookingDTO.getEvent().getName());
    }

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage() {
        this.message = message;
    }

    private String generateBookingMessage(String eventTitle) {
        StringBuilder message = new StringBuilder();
        message.append("Congratulations! ");
        message.append("'").append(eventTitle).append("'").append(" Event ");
        message.append("Booked successfully.");
        return message.toString();
    }
}
