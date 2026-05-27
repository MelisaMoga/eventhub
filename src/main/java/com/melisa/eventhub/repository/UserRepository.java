package com.melisa.eventhub.repository;

import com.melisa.eventhub.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}