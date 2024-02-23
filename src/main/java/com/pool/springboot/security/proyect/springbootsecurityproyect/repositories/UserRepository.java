package com.pool.springboot.security.proyect.springbootsecurityproyect.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.pool.springboot.security.proyect.springbootsecurityproyect.entities.User;



public interface UserRepository extends CrudRepository<User, Long>{

    boolean existsByUsername(String username);
    Optional<User> findByUsername(String username);

    @Query("SELECT deleteuser(?1)")
    boolean deleteUserById(Long Id);
}
