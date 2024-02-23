package com.pool.springboot.security.proyect.springbootsecurityproyect.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pool.springboot.security.proyect.springbootsecurityproyect.entities.Product;
import com.pool.springboot.security.proyect.springbootsecurityproyect.services.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping
    public List<Product> list(){
        return service.findAll();
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Product product, BindingResult result){
        if(result.hasFieldErrors()){
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(product));
    }

    private ResponseEntity<?> validation(BindingResult result){
        Map<String, String> erros = new HashMap<>();

      result.getFieldErrors().forEach(err ->{
         erros.put(err.getField(), "El campo "+ err.getField()+" "+ err.getDefaultMessage());
      });
      return ResponseEntity.badRequest().body(erros);
    }
}
