package com.pool.springboot.security.proyect.springbootsecurityproyect.entities;

import java.util.List;

import org.springframework.data.annotation.Id;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "histories")
public class History {

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
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany
    @JoinTable(
        name = "histies_tipesH",
        joinColumns = @JoinColumn(name = "histori_id"),
        inverseJoinColumns = @JoinColumn(name = "tipeh_id"),
        uniqueConstraints = {@UniqueConstraint(columnNames = {"histori_id", "tipeh_id"})}
    )
    private List<TipeH> tipeHs;

}
