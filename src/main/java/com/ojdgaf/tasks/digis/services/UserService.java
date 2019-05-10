package com.ojdgaf.tasks.digis.services;

import com.ojdgaf.tasks.digis.entities.User;
import com.ojdgaf.tasks.digis.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public List<User> findAll() {
        return repository.findAll();
    }

    public User findById(Integer id) {
        return repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User with specified id is not found")
        );
    }

    public User save(User user) {
        if (repository.existsByLogin(user.getLogin()))
            throw new RuntimeException("Login is already in use");

        return repository.save(user);
    }

    public User update(Integer id, User user) {
        user.setId(id);

        return repository.save(user);
    }
}
