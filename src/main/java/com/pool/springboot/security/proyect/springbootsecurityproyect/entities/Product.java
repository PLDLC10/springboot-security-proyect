package com.pool.springboot.security.proyect.springbootsecurityproyect.entities;

import java.util.List;
import java.util.Set;

import org.hibernate.annotations.ManyToAny;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;


@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    //@IsExistsDb
    //@IsRequired
    @NotBlank
    private String sku;

    @NotNull
    private Integer price;

    @NotNull
    private Integer stock;

    @NotBlank
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User users;

    @JsonIgnoreProperties({"products", "handler", "hibernateLazyInitializer"})
    @ManyToAny
    @JoinTable(
        name = "products_tipes",
        joinColumns = @JoinColumn(name = "product_id"),
        inverseJoinColumns = @JoinColumn(name = "tipe_id"),
        uniqueConstraints = {@UniqueConstraint(columnNames = {"product_id", "tipe_id"})})
    private List<Tipe> tipes;


    @Transient
    @NotEmpty
    private Set<String> product;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUsers() {
        return users;
    }

    public void setUsers(User users) {
        this.users = users;
    }

    public List<Tipe> getTipes() {
        return tipes;
    }

    public void setTipes(List<Tipe> tipes) {
        this.tipes = tipes;
    }

    public Set<String> getProduct() {
        return product;
    }

    public void setProduct(Set<String> product) {
        this.product = product;
    }

    
}
