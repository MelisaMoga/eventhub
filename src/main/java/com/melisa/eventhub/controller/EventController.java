package com.melisa.eventhub.controller;

import com.melisa.eventhub.model.Category;
import com.melisa.eventhub.model.Event;
import com.melisa.eventhub.model.User;
import com.melisa.eventhub.repository.CategoryRepository;
import com.melisa.eventhub.repository.EventRepository;
import com.melisa.eventhub.repository.UserRepository;
import org.springframework.web.bind.annotation.*;
import com.melisa.eventhub.dto.CreateEventRequest;
import io.swagger.v3.oas.annotations.Operation;
import java.time.LocalDateTime;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;


import java.util.List;

@RestController
@RequestMapping("/api/events")
@SecurityRequirement(name = "bearerAuth")
public class EventController {

    private final EventRepository repository;
    private final UserRepository organizerRepository;
    private final CategoryRepository categoryRepository;

    public EventController(EventRepository repository,
                           UserRepository organizerRepository,
                           CategoryRepository categoryRepository) {
        this.repository = repository;
        this.organizerRepository = organizerRepository;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping
    public List<Event> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Event getById(@PathVariable Long id) {
        return repository.findById(id).orElseThrow();
    }

    @PostMapping
    @Operation(summary = "Create a new event", description = "Creates a new event. Requires authentication.")
    public Event create(@RequestBody CreateEventRequest request) {
        User organizer = organizerRepository.findById(request.getOrganizerId()).orElseThrow();
        Category category = categoryRepository.findById(request.getCategoryId()).orElseThrow();

        Event event = new Event(
                request.getTitle(),
                request.getDescription(),
                request.getLocation(),
                LocalDateTime.parse(request.getEventDate()),
                request.getMaxParticipants(),
                organizer,
                category
        );

        return repository.save(event);
    }

    @PutMapping("/{id}")
    public Event update(@PathVariable Long id, @RequestBody Event updated) {
        return repository.findById(id).map(event -> {
            event.setTitle(updated.getTitle());
            event.setDescription(updated.getDescription());
            event.setLocation(updated.getLocation());
            event.setEventDate(updated.getEventDate());
            event.setMaxParticipants(updated.getMaxParticipants());
            event.setCategory(updated.getCategory());
            return repository.save(event);
        }).orElseThrow();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
