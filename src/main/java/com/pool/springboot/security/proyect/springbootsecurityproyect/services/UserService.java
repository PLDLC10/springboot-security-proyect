package com.pool.springboot.security.proyect.springbootsecurityproyect.services;

import java.util.List;

import com.pool.springboot.security.proyect.springbootsecurityproyect.entities.User;

public interface UserService {

    List<User> finAll();

    User save(User user);
    
}
