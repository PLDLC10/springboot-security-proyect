package com.pool.springboot.security.proyect.springbootsecurityproyect.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pool.springboot.security.proyect.springbootsecurityproyect.entities.User;
import com.pool.springboot.security.proyect.springbootsecurityproyect.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository repository;

    @Override
    public List<User> finAll() {
        return (List<User>)repository.findAll();
    }

    @Override
    public User Save(User user) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'Save'");
    }

    
    

}
