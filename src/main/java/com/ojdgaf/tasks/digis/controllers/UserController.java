package com.ojdgaf.tasks.digis.controllers;

import com.ojdgaf.tasks.digis.entities.User;
import com.ojdgaf.tasks.digis.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService service;

    @GetMapping
    public List<User> findAll() {
        return service.findAll();
    }

    @GetMapping("{id}")
    public User findById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PostMapping
    public User save(@RequestBody @Valid User user) {
        return service.save(user);
    }

    @PutMapping("{id}")
    public User update(@PathVariable("id") Integer id, @RequestBody @Valid User user) {
        return service.update(id, user);
    }
}
