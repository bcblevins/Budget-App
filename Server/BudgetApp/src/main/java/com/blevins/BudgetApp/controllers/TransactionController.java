package com.blevins.BudgetApp.controllers;

import com.blevins.BudgetApp.daos.TransactionDao;
import com.blevins.BudgetApp.models.Transaction;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
@CrossOriginnpm
public class TransactionController {
    private final TransactionDao transactionDao;

    public TransactionController(TransactionDao transactionDao) {
        this.transactionDao = transactionDao;
    }

    @GetMapping
    public List<Transaction> getAll() {
        return transactionDao.getAll();
    }

    @GetMapping("/{id}")
    public Transaction getOne(@PathVariable int id) {
        return transactionDao.get(id);
    }

    @PostMapping
    public Transaction create (@RequestBody Transaction transaction) {
        if (transaction.getDate() == null) {
            transaction.setDate(java.time.LocalDateTime.now());
        }
        return transactionDao.create(transaction);
    }

    @PutMapping("/{id}")
    public Transaction update (@RequestBody Transaction transaction) {
        return transactionDao.update(transaction);
    }

    @DeleteMapping("/{id}")
    public boolean delete (@PathVariable int id) {
        return transactionDao.delete(id);
    }
}
