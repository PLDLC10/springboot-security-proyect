package com.pool.springboot.security.proyect.springbootsecurityproyect.validations;

import org.springframework.beans.factory.annotation.Autowired;

import com.pool.springboot.security.proyect.springbootsecurityproyect.services.ProductService;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ExistsDbValidation implements ConstraintValidator<IsExistsDb, String>{

    @Autowired
    private ProductService service;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (service == null) {
            return true;
        }
        return !service.existsBySku(value);
    }

}
