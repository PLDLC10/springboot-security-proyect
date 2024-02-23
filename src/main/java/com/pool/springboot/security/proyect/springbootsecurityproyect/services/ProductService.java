package com.pool.springboot.security.proyect.springbootsecurityproyect.services;

import java.util.List;

import com.pool.springboot.security.proyect.springbootsecurityproyect.entities.Product;

public interface ProductService {

    List<Product> findAll();

    Product save(Product product);

    boolean existsBySku(String sku);
    
}
