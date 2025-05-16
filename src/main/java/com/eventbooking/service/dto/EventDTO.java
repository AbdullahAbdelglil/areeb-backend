package com.eventbooking.service.dto;

import jakarta.persistence.Lob;
import jakarta.validation.constraints.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;


/**
 * A DTO for the {@link com.eventbooking.domain.Event} entity.
 */
public class EventDTO implements Serializable {

    private Long id;

    @NotNull(message = "is mandatory")
    @NotBlank(message = "not allowed to be blank")
    private String name;

    @NotNull(message = "is mandatory")
    private String description;

    @Lob
    private String agenda;

    private Integer categoryId;

    @NotNull(message = "is required")
    @Future(message = "must be in the future")
    private LocalDate date;

    @NotNull(message = "is required")
    private String venue;

    private BigDecimal price = new BigDecimal("0.0");

    private String imageUrl;

    private Date createdAt;

    private Date updatedAt;

    public EventDTO() {
    }

    public EventDTO(Long id,
                    String name,
                    String description,
                    String agenda,
                    Integer categoryId,
                    LocalDate date,
                    String venue,
                    BigDecimal price,
                    String imageUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.agenda = agenda;
        this.categoryId = categoryId;
        this.date = date;
        this.venue = venue;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAgenda() {
        return agenda;
    }

    public void setAgenda(String agenda) {
        this.agenda = agenda;
    }

    public Integer getCategory() {
        return categoryId;
    }

    public void setCategory(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "EventDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", category='" + getCategory() + "'" +
            ", date='" + getDate() + "'" +
            ", venue='" + getVenue() + "'" +
            ", price=" + getPrice() +
            ", imageUrl='" + getImageUrl() + "'" +
            "}";
    }
}
