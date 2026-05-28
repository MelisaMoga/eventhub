package com.melisa.eventhub.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Request body for creating a new event")
public class CreateEventRequest {

    @Schema(description = "Title of the event", example = "Photography Walk")
    private String title;

    @Schema(description = "Description of the event", example = "Explore the city and take photos together")
    private String description;

    @Schema(description = "Location of the event", example = "Bucharest")
    private String location;

    @Schema(description = "Date and time of the event", example = "2026-06-15T10:00:00")
    private String eventDate;

    @Schema(description = "Maximum number of participants", example = "8")
    private int maxParticipants;

    @Schema(description = "ID of the organizer", example = "1")
    private Long organizerId;

    @Schema(description = "ID of the category", example = "1")
    private Long categoryId;

    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getLocation() { return location; }
    public String getEventDate() { return eventDate; }
    public int getMaxParticipants() { return maxParticipants; }
    public Long getOrganizerId() { return organizerId; }
    public Long getCategoryId() { return categoryId; }
}
