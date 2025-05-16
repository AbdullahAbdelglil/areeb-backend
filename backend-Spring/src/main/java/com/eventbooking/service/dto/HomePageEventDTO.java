package com.eventbooking.service.dto;


import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;


public class HomePageEventDTO implements Serializable {
    private Long id;
    private String title;
    private String description;
    private Integer categoryId;
    private String category;
    private String imageUrl;
    private LocalDate date;
    private BigDecimal price;
    private boolean booked;


    public HomePageEventDTO(Long id,
                            String title,
                            String description,
                            Integer categoryId,
                            String imageUrl,
                            LocalDate date,
                            BigDecimal price,
                            boolean booked) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.categoryId = categoryId;
        this.imageUrl = imageUrl;
        this.date = date;
        this.price = price;
        this.booked = booked;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public boolean isBooked() {
        return booked;
    }

    public void setBooked(boolean booked) {
        this.booked = booked;
    }
}
