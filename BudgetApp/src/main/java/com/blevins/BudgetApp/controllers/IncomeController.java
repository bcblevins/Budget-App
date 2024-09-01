package com.blevins.BudgetApp.controllers;

import com.blevins.BudgetApp.daos.IncomeDao;
import com.blevins.BudgetApp.models.Income;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/incomes")
public class IncomeController {
    private final IncomeDao incomeDao;

    public IncomeController(IncomeDao incomeDao) {
        this.incomeDao = incomeDao;
    }

    @GetMapping
    public List<Income> getAll() {
        return incomeDao.getAll();
    }

    @GetMapping("/{id}")
    public Income getOne(@PathVariable int id) {
        return incomeDao.get(id);
    }

    @PostMapping
    public Income create (@RequestBody Income income) {
        return incomeDao.create(income);
    }

    @PutMapping("/{id}")
    public Income update (@RequestBody Income income) {
        return incomeDao.update(income);
    }

    @DeleteMapping("/{id}")
    public boolean delete (@PathVariable int id) {
        return incomeDao.delete(id);
    }
}
