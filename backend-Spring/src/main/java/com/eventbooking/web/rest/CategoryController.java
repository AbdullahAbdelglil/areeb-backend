package com.eventbooking.web.rest;

import com.eventbooking.domain.Category;
import com.eventbooking.repository.CategoryRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {

    private final CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/")
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
}
