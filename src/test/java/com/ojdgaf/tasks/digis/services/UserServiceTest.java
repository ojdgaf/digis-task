package com.ojdgaf.tasks.digis.services;

import java.util.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import com.ojdgaf.tasks.digis.TestConfig;
import com.ojdgaf.tasks.digis.entities.User;
import com.ojdgaf.tasks.digis.repositories.UserRepository;
import com.ojdgaf.tasks.digis.exceptions.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import uk.co.jemos.podam.api.PodamFactory;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@Import(TestConfig.class)
@ExtendWith(SpringExtension.class)
class UserServiceTest {
    @Autowired
    private UserService service;

    @MockBean
    private UserRepository repository;

    @Autowired
    private PodamFactory factory;

    private User mockUser() {
        return factory.manufacturePojoWithFullData(User.class);
    }

    @Test
    void findAllTest() {
        List<User> users = Arrays.asList(mockUser(), mockUser(), mockUser());
        when(repository.findAll()).thenReturn(users);
        assertEquals(service.findAll(), users);
    }

    @Test
    void findByExistingIdTest() {
        User user = mockUser();
        when(repository.findById(user.getId())).thenReturn(Optional.of(user));
        assertEquals(service.findById(user.getId()), user);
    }

    @Test
    void findByNonexistentIdTest() {
        User user = mockUser();
        when(repository.findById(user.getId())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> service.findById(user.getId()));
    }

    @Test
    void saveWithUniqueLoginTest() {
        User user = mockUser();
        when(repository.existsByLogin(user.getLogin())).thenReturn(false);
        when(repository.save(user)).thenReturn(user);
        assertEquals(service.save(user), user);
    }

    @Test
    void saveWithExistingLoginTest() {
        User user = mockUser();
        when(repository.existsByLogin(user.getLogin())).thenReturn(true);
        assertThrows(BadRequestException.class, () -> service.save(user));
    }

    @Test
    void updateWithNonexistentIdTest() {
        User user = mockUser();
        when(repository.findById(user.getId())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> service.update(user.getId(), user));
    }

    @Test
    void updateWithOldLoginTest() {
        User oldUser = mockUser();
        User newUser = mockUser();
        newUser.setId(oldUser.getId());
        newUser.setLogin(oldUser.getLogin());
        when(repository.findById(newUser.getId())).thenReturn(Optional.of(oldUser));
        when(repository.existsByLogin(newUser.getLogin())).thenReturn(true);
        when(repository.save(newUser)).thenReturn(newUser);
        assertEquals(service.update(newUser.getId(), newUser), newUser);
    }

    @Test
    void updateWithNewUniqueLoginTest() {
        User oldUser = mockUser();
        User newUser = mockUser();
        newUser.setId(oldUser.getId());
        when(repository.findById(newUser.getId())).thenReturn(Optional.of(oldUser));
        when(repository.existsByLogin(newUser.getLogin())).thenReturn(false);
        when(repository.save(newUser)).thenReturn(newUser);
        assertEquals(service.update(newUser.getId(), newUser), newUser);
    }

    @Test
    void updateWithNewExistingLoginTest() {
        User oldUser = mockUser();
        User newUser = mockUser();
        newUser.setId(oldUser.getId());
        when(repository.findById(newUser.getId())).thenReturn(Optional.of(oldUser));
        when(repository.existsByLogin(newUser.getLogin())).thenReturn(true);
        when(repository.save(newUser)).thenReturn(newUser);
        assertThrows(BadRequestException.class, () -> service.update(newUser.getId(), newUser));
    }
}
