package com.melisa.eventhub.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String description;
    private String location;
    private LocalDateTime eventDate;
    private int maxParticipants;
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "organizer_id")
    private User organizer;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Event() {}

    public Event(String title, String description, String location,
                 LocalDateTime eventDate, int maxParticipants,
                 User organizer, Category category) {
        this.title = title;
        this.description = description;
        this.location = location;
        this.eventDate = eventDate;
        this.maxParticipants = maxParticipants;
        this.organizer = organizer;
        this.category = category;
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getLocation() { return location; }
    public LocalDateTime getEventDate() { return eventDate; }
    public int getMaxParticipants() { return maxParticipants; }
    public User getOrganizer() { return organizer; }
    public Category getCategory() { return category; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setTitle(String title) { this.title = title; }
    public void setDescription(String description) { this.description = description; }
    public void setLocation(String location) { this.location = location; }
    public void setEventDate(LocalDateTime eventDate) { this.eventDate = eventDate; }
    public void setMaxParticipants(int maxParticipants) { this.maxParticipants = maxParticipants; }
    public void setCategory(Category category) { this.category = category; }
    public void setCreatedAt(java.time.LocalDateTime createdAt) { this.createdAt = createdAt; }
    public void setOrganizer(User organizer) { this.organizer = organizer; }
}