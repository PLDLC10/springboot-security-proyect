package com.pool.springboot.security.proyect.springbootsecurityproyect.validations;








import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class RequiredValidation implements ConstraintValidator<IsRequired, String>{

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        System.out.println("value :------->"+value);
        //return StringUtils.hasText(value);
        return false;
        //false el campo esta vacio
        //true el campo tiene texto
    }

}
