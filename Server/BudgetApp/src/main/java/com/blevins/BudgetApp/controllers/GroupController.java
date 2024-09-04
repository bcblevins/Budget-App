package com.blevins.BudgetApp.controllers;

import com.blevins.BudgetApp.models.Group;
import org.springframework.web.bind.annotation.*;
import com.blevins.BudgetApp.daos.GroupDao;

import java.util.List;

@RestController
@RequestMapping("/groups")
@CrossOrigin
public class GroupController {
    private final GroupDao groupDao;

    public GroupController(GroupDao groupDao) {
        this.groupDao = groupDao;
    }

    @GetMapping
    public List<Group> getAll() {
        return groupDao.getAll();
    }

    @GetMapping("/{id}")
    public Group get(@PathVariable int id) {
        return groupDao.get(id);
    }

    @PostMapping
    public Group create(@RequestBody Group group) {
        return groupDao.create(group);
    }

    @PutMapping("/{id}")
    public Group update(@PathVariable int id, @RequestBody Group group) {
        group.setId(id);
        return groupDao.update(group);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        groupDao.delete(id);
    }

}
