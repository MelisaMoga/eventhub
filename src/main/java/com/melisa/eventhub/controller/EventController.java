package com.melisa.eventhub.controller;

import com.melisa.eventhub.model.Event;
import com.melisa.eventhub.repository.EventRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {

    private final EventRepository repository;

    public EventController(EventRepository repository) {
        this.repository = repository;
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
    public Event create(@RequestBody Event event) {
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
