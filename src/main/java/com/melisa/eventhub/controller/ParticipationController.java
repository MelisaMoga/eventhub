package com.melisa.eventhub.controller;

import com.melisa.eventhub.model.Participation;
import com.melisa.eventhub.repository.EventRepository;
import com.melisa.eventhub.repository.ParticipationRepository;
import com.melisa.eventhub.repository.UserRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/participations")
@SecurityRequirement(name = "bearerAuth")
public class ParticipationController {

    private final ParticipationRepository participationRepo;
    private final UserRepository userRepo;
    private final EventRepository eventRepo;

    public ParticipationController(ParticipationRepository participationRepo,
                                   UserRepository userRepo,
                                   EventRepository eventRepo) {
        this.participationRepo = participationRepo;
        this.userRepo = userRepo;
        this.eventRepo = eventRepo;
    }

    @GetMapping
    public List<Participation> getAll() {
        return participationRepo.findAll();
    }

    @PostMapping("/join")
    public Participation joinEvent(@RequestParam Long userId, @RequestParam Long eventId) {
        var user = userRepo.findById(userId).orElseThrow();
        var event = eventRepo.findById(eventId).orElseThrow();
        return participationRepo.save(new Participation(user, event));
    }

    @PutMapping("/{id}/approve")
    public Participation approve(@PathVariable Long id) {
        return participationRepo.findById(id).map(p -> {
            p.setStatus(Participation.Status.APPROVED);
            return participationRepo.save(p);
        }).orElseThrow();
    }

    @PutMapping("/{id}/reject")
    public Participation reject(@PathVariable Long id) {
        return participationRepo.findById(id).map(p -> {
            p.setStatus(Participation.Status.REJECTED);
            return participationRepo.save(p);
        }).orElseThrow();
    }
}