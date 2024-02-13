package com.pool.springboot.security.proyect.springbootsecurityproyect.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pool.springboot.security.proyect.springbootsecurityproyect.entities.User;
import com.pool.springboot.security.proyect.springbootsecurityproyect.services.UserService;


import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService service;

   public List<User> list(){
      return service.finAll();
   }

   
   @PostMapping("/foo")
   public ResponseEntity<?> create(@Valid @RequestBody User user, BindingResult result){
      if (result.hasFieldErrors()) {
         return validation(result);
      }
      return ResponseEntity.status(HttpStatus.CREATED).body(service.save(user));
   }

   private ResponseEntity<?> validation(BindingResult result){
      Map<String, String> erros = new HashMap<>();

      result.getFieldErrors().forEach(err ->{
         erros.put(err.getField(), "El campo "+ err.getField()+" "+ err.getDefaultMessage());
      });
      return ResponseEntity.badRequest().body(erros);
   }
}
