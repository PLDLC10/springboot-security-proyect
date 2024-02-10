package com.pool.springboot.security.proyect.springbootsecurityproyect.entities;

import java.util.List;

import org.hibernate.annotations.ManyToAny;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;


@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;
    @NotBlank
    private String sku;
    @NotBlank
    private Integer price;
    @NotBlank
    private Integer stock;
    @NotBlank
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User users;

    @ManyToAny
    @JoinTable(
        name = "tipes_products",
        joinColumns = @JoinColumn(name = "tipe_id"),
        inverseJoinColumns = @JoinColumn(name = "product_id"),
        uniqueConstraints = {@UniqueConstraint(columnNames = {"tipe_id", "product_id"})})
    private List<Tipe> tipes;
}
