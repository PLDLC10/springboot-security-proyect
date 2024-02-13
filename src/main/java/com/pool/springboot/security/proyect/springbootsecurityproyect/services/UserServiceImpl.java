package com.pool.springboot.security.proyect.springbootsecurityproyect.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pool.springboot.security.proyect.springbootsecurityproyect.entities.Role;
import com.pool.springboot.security.proyect.springbootsecurityproyect.entities.User;
import com.pool.springboot.security.proyect.springbootsecurityproyect.repositories.RoleRepository;
import com.pool.springboot.security.proyect.springbootsecurityproyect.repositories.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository repository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<User> finAll() {
        return (List<User>)repository.findAll();
    }

    @Transactional
    @Override
    public User save(User user) {    
        Optional<Role> opRole = roleRepository.findByName("ROLE_USER");
        List<Role> role = new ArrayList<>();
        opRole.ifPresent(role::add);
        Optional<Role> opRoleAdmin = roleRepository.findByName("ROLE_ADMIN");
        opRoleAdmin.ifPresent(role::add);
        
        user.setRoles(role);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repository.save(user);
    }

    @Override
    public boolean existsByUsername(String username) {
        return repository.existsByUsername(username);
    }
}
