package com.pool.springboot.security.proyect.springbootsecurityproyect.repositories;

import org.springframework.data.repository.CrudRepository;

import com.pool.springboot.security.proyect.springbootsecurityproyect.entities.User;

public interface UserRepository extends CrudRepository<User, Long>{

    boolean existsByUsername(String username);
}
