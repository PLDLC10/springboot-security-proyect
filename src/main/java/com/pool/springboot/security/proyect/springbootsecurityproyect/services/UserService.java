package com.pool.springboot.security.proyect.springbootsecurityproyect.services;

import java.util.List;
import java.util.Optional;

import com.pool.springboot.security.proyect.springbootsecurityproyect.entities.User;

public interface UserService {

    List<User> finAll();

    User save(User user);

    boolean existsByUsername(String username);

    Optional<User> update (Long id, User user);

    boolean delete (Long id);
}
