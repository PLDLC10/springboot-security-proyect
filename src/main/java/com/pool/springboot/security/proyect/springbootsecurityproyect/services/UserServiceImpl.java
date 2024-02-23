package com.pool.springboot.security.proyect.springbootsecurityproyect.services;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Set<Role> role = new HashSet<>();
   
        if (authentication !=null && user.getRole() !=null) {
            if (authentication.getAuthorities().stream().anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"))) {
                if (user.getRole().toUpperCase().equals("USER")) {
                    role.add(roleRepository.findByName("ROLE_USER").orElse(null));
                }else{
                    role.add(roleRepository.findByName("ROLE_ADMIN").orElse(null));
                    role.add(roleRepository.findByName("ROLE_USER").orElse(null));
                }
            }else{
                role.add(roleRepository.findByName("ROLE_CLIENT").orElse(null));
            }
        }else{
            role.add(roleRepository.findByName("ROLE_CLIENT").orElse(null));
        }
    
        user.setRoles(role);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repository.save(user);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER', 'CLIENT')")
    @Override
    public Optional<User> update(Long id, User user) {
        Optional <User> userOp = repository.findById(id);
        if (userOp.isPresent()) {
            User userDb = userOp.orElseThrow();
            userDb.setUsername(user.getUsername());
            userDb.setPassword(passwordEncoder.encode(user.getPassword()));
            return Optional.of(repository.save(userDb));
        }
        return userOp;
    }
 @Override
    public boolean delete(Long id) {
        return repository.deleteUserById(id);
    }
    

    @Override
    public boolean existsByUsername(String username) {
        return repository.existsByUsername(username);
    }


}
