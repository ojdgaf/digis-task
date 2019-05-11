package com.ojdgaf.tasks.digis.services;

import java.util.List;

import com.ojdgaf.tasks.digis.entities.User;
import com.ojdgaf.tasks.digis.exceptions.BadRequestException;
import com.ojdgaf.tasks.digis.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

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
            throw new BadRequestException("Login is already in use");

        return repository.save(user);
    }

    public User update(Integer id, User userDetails) {
        User user = findById(id);

        String oldLogin = user.getLogin(), newLogin = userDetails.getLogin();

        if (!newLogin.equals(oldLogin) && repository.existsByLogin(newLogin))
            throw new BadRequestException("New login is already in use");

        user.setLogin(newLogin);
        user.setFullName(userDetails.getFullName());
        user.setDateOfBirth(userDetails.getDateOfBirth());
        user.setGender(userDetails.getGender());

        return repository.save(user);
    }
}
