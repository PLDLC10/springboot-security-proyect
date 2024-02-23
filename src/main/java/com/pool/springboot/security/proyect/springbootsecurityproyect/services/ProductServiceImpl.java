package com.pool.springboot.security.proyect.springbootsecurityproyect.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pool.springboot.security.proyect.springbootsecurityproyect.entities.Product;
import com.pool.springboot.security.proyect.springbootsecurityproyect.entities.Tipe;
import com.pool.springboot.security.proyect.springbootsecurityproyect.entities.User;
import com.pool.springboot.security.proyect.springbootsecurityproyect.repositories.ProductRepository;
import com.pool.springboot.security.proyect.springbootsecurityproyect.repositories.TipeRepository;
import com.pool.springboot.security.proyect.springbootsecurityproyect.repositories.UserRepository;



@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository repository;
    @Autowired
    private TipeRepository TipeRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Product> findAll() {
        return (List<Product>) repository.findAll();
    }

    @Override
    public Product save(Product product) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User user = userRepository.findByUsername(authentication.getName()).orElse(null);
    
        if (product.getProduct() !=null){
            Set<Tipe> tipe = product.getProduct().stream()
            .map(iten -> TipeRepository.findByName(iten).orElse(null))
            .filter(Objects::nonNull)
            .collect(Collectors.toSet());

            product.setTipes(new ArrayList<>(tipe));
            product.setUsers(user);

        }else{
            System.out.println("el valor es nulo: ----------");
        }
        return repository.save(product);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsBySku(String sku) {
        return repository.existsBySku(sku);
    }

}
