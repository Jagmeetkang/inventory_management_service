package com.inventory_managment_service.product.model;

import jakarta.persistence.*;
import lombok.*;

@Entity

@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String sku;
    private String name;
    private int stock;

    public Product() {
    }

    public Long getId() {
        return id;
    }

    public Product(Long id, String sku, String name, int stock) {
        this.id = id;
        this.sku = sku;
        this.name = name;
        this.stock = stock;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }



}
