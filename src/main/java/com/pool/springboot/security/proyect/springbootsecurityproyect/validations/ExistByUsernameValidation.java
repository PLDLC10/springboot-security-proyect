package com.pool.springboot.security.proyect.springbootsecurityproyect.validations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pool.springboot.security.proyect.springbootsecurityproyect.services.UserService;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class ExistByUsernameValidation implements ConstraintValidator<IExistByUsername, String>{

    @Autowired
    private UserService service;

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        if (service==null) {
            return true;
        }
        return !service.existsByUsername(username);
    }

}
