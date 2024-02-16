package com.pool.springboot.security.proyect.springbootsecurityproyect.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pool.springboot.security.proyect.springbootsecurityproyect.entities.Product;
import com.pool.springboot.security.proyect.springbootsecurityproyect.repositories.ProductRepository;



@Service
public class ProductServiceImpl implements ProductService{

    private ProductRepository repository;

    @Override
    public List<Product> findAll() {
        return (List<Product>) repository.findAll();
    }

    @Override
    public Product save(Product product) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsBySku(String sku) {
        return repository.existsBySku(sku);
    }

}
