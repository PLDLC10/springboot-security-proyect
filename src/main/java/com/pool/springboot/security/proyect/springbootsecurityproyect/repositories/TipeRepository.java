package com.pool.springboot.security.proyect.springbootsecurityproyect.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.pool.springboot.security.proyect.springbootsecurityproyect.entities.Tipe;


public interface TipeRepository extends CrudRepository<Tipe, Long>{

    Optional<Tipe> findByName(String name);
}
