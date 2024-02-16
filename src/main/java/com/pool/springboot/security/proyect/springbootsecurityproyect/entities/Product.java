package com.pool.springboot.security.proyect.springbootsecurityproyect.entities;

import java.util.List;

import org.hibernate.annotations.ManyToAny;

import com.pool.springboot.security.proyect.springbootsecurityproyect.validations.IsExistsDb;
import com.pool.springboot.security.proyect.springbootsecurityproyect.validations.IsRequired;

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


@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @IsExistsDb
    @IsRequired
    private String sku;

    @IsRequired
    private Integer price;

    @IsRequired
    private Integer stock;

    @IsRequired
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

    @Transient
    private String product;

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

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    
}
