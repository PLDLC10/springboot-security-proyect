package com.pool.springboot.security.proyect.springbootsecurityproyect.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

   @PreAuthorize("hasRole('ADMIN')")
   @GetMapping
   public List<User> list(){
      return service.finAll();
   }
   @PostMapping
   public ResponseEntity<?> create(@Valid @RequestBody User user, BindingResult result){
      if (result.hasFieldErrors()) {
         return validation(result);
      }
      return ResponseEntity.status(HttpStatus.CREATED).body(service.save(user));
   }
   @PutMapping("/{id}")
   public ResponseEntity<?> update(@Valid @RequestBody User user, BindingResult result, @PathVariable Long id){
      if (result.hasFieldErrors()) {
         return validation(result);
      }
      Optional<User> userOp = service.update(id, user);
      if (userOp.isPresent()) {
         return ResponseEntity.status(HttpStatus.CREATED).body(userOp.orElseThrow());
      }
      return ResponseEntity.notFound().build();
   }
   @PreAuthorize("hasRole('ADMIN')")
   @DeleteMapping("/{id}")
   public ResponseEntity<?> delete(@Valid @RequestBody User user, BindingResult result, @PathVariable Long id){
      boolean userOp = service.delete(id);
      if (userOp = true) {
         return ResponseEntity.status(HttpStatus.CREATED).body("usuario enable false");
      }else if(userOp = false){
         return ResponseEntity.status(HttpStatus.CREATED).body("usuario eliminado");
      }
      return ResponseEntity.notFound().build();
   } 

   private ResponseEntity<?> validation(BindingResult result){
      Map<String, String> erros = new HashMap<>();

      result.getFieldErrors().forEach(err ->{
         erros.put(err.getField(), "El campo "+ err.getField()+" "+ err.getDefaultMessage());
      });
      return ResponseEntity.badRequest().body(erros);
   }
}
