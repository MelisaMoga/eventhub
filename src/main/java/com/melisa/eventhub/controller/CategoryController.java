package com.melisa.eventhub.controller;

import com.melisa.eventhub.model.Category;
import com.melisa.eventhub.repository.CategoryRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@SecurityRequirement(name = "bearerAuth")
public class CategoryController {

    private final CategoryRepository repository;

    public CategoryController(CategoryRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Category> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Category getById(@PathVariable Long id) {
        return repository.findById(id).orElseThrow();
    }

    @PostMapping
    public Category create(@RequestBody Category category) {
        return repository.save(category);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
