package com.pool.springboot.security.proyect.springbootsecurityproyect.repositories;

import org.springframework.data.repository.CrudRepository;

import com.pool.springboot.security.proyect.springbootsecurityproyect.entities.Role;
import java.util.Optional;


public interface RoleRepository extends CrudRepository<Role, Long>{

    Optional<Role> findByName(String name);

}
