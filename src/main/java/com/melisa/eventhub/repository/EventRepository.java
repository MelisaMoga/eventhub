package com.melisa.eventhub.repository;

import com.melisa.eventhub.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}