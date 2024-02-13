package com.pool.springboot.security.proyect.springbootsecurityproyect.validations;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;



@Constraint(validatedBy = ExistByUsernameValidation.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface IExistByUsername {

    String message() default "ya existe!, escoja otro username";
    
    Class<?>[] groups() default{};

    Class<? extends Payload>[] payload() default {};
}
