package com.blevins.BudgetApp.controllers;

import com.blevins.BudgetApp.daos.CategoryDao;
import com.blevins.BudgetApp.models.Category;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryDao categoryDao;

    public CategoryController(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @GetMapping
    public List<Category> getAll() {
        return categoryDao.getAll();
    }

    @GetMapping("/{id}")
    public Category getOne(@PathVariable int id) {
        return categoryDao.get(id);
    }

    @PostMapping
    public Category create (@RequestBody Category category) {
        return categoryDao.create(category);
    }

    @PutMapping("/{id}")
    public Category update (@RequestBody Category category) {
        return categoryDao.update(category);
    }

    @DeleteMapping("/{id}")
    public boolean delete (@PathVariable int id) {
        return categoryDao.delete(id);
    }
}
