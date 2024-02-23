package com.pool.springboot.security.proyect.springbootsecurityproyect.repositories;

import org.springframework.data.repository.CrudRepository;

import com.pool.springboot.security.proyect.springbootsecurityproyect.entities.Product;

public interface ProductRepository extends CrudRepository<Product, Long>{

    boolean existsBySku(String sku);

    //boolean findByUser_id(Long id);

}
