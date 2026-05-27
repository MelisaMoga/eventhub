package com.melisa.eventhub.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "participations")
public class Participation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @Enumerated(EnumType.STRING)
    private Status status;

    private LocalDateTime joinedAt;

    public enum Status {
        PENDING, APPROVED, REJECTED
    }

    public Participation() {}

    public Participation(User user, Event event) {
        this.user = user;
        this.event = event;
        this.status = Status.PENDING;
        this.joinedAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public User getUser() { return user; }
    public Event getEvent() { return event; }
    public Status getStatus() { return status; }
    public LocalDateTime getJoinedAt() { return joinedAt; }
    public void setStatus(Status status) { this.status = status; }
}