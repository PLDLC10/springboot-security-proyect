package com.pool.springboot.security.proyect.springbootsecurityproyect.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pool.springboot.security.proyect.springbootsecurityproyect.entities.User;
import com.pool.springboot.security.proyect.springbootsecurityproyect.repositories.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class JpaUserDetailsService implements UserDetailsService{

    @Autowired
    private UserRepository repository;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = repository.findByUsername(username);

        if (userOptional.isEmpty()) {
            throw new UsernameNotFoundException(String.format("Username %s no existe en el sistema", username));
        }
        User user = userOptional.orElseThrow();

        List<GrantedAuthority> authorities = user.getRoles().stream()
        .map(role -> new SimpleGrantedAuthority(role.getName()))
        .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(user.getUsername(),
        user.getPassword(),
        user.isEnable(),
        true,
        true,
        true,
        authorities);
    }

}
