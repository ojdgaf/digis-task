package com.ojdgaf.tasks.digis.repositories;

import java.util.Optional;

import com.ojdgaf.tasks.digis.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByLogin(String login);

    Optional<User> findByFullName(String fullName);

    Boolean existsByLogin(String login);
}

