package com.pool.springboot.security.proyect.springbootsecurityproyect.validations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pool.springboot.security.proyect.springbootsecurityproyect.repositories.ProductRepository;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class ExistsDbValidation implements ConstraintValidator<IsExistsDb, String>{

    @Autowired
    private ProductRepository service;

    @Override
    public boolean isValid(String sku, ConstraintValidatorContext context) {
        if (service == null) {
            return true;
        }
        System.out.println("sku: ------>"+ sku);
        return service.existsBySku(sku);
        
        //true no existe
        //false existe
    }

}
