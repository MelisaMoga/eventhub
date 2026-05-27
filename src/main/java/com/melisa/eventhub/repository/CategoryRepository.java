package com.melisa.eventhub.repository;

import com.melisa.eventhub.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}