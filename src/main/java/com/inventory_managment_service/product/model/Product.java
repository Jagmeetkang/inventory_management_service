package com.inventory_managment_service.product.model;

import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


@Entity

@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Product SKU cannot be empty")
    private String sku;

    @NotBlank(message = "Product name cannot be empty")
    private String name;

    @NotNull(message = "Stock quantity cannot be null")
    @Min(value = 1, message = "Stock must be at least 1")
    private int stock;

    @NotNull(message = "Price cannot be null")
    @Min(value = 0, message = "Price must be a positive value")
    private Double price;

    public Product() {
    }

    public Long getId() {
        return id;
    }


    public Product(Long id, String sku, String name, int stock, Double price) {
        this.id = id;
        this.sku = sku;
        this.name = name;
        this.stock = stock;
        this.price = price;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }



}
