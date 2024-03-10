package com.azizONeill.product.model;


import com.azizONeill.product.model.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "Product")
public class Product {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "product_id", updatable = false, nullable = false)
    private UUID id;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "status", columnDefinition = "integer")
    private Status status;

    @NotNull
    @Positive
    @Column(name = "price", precision = 10, scale = 2)
    private BigDecimal price;

    @NotNull
    @PositiveOrZero
    @Column(name = "stockQuantity")
    private int stockQuantity;

    @NotNull
    @Column(name = "imageUrl")
    private String imageUrl;

    @NotNull
    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SUBCATEGORY_ID")
    private Subcategory subcategory;


    public Product(UUID id, @NotNull String name, @NotNull Status status, @NotNull @Positive BigDecimal price, @NotNull @PositiveOrZero int stockQuantity, @NotNull String imageUrl, @NotNull String description, Subcategory subcategory) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.imageUrl = imageUrl;
        this.description = description;
        this.subcategory = subcategory;
    }

    public Product() {
    }

    public UUID getId() {
        return this.id;
    }

    public @NotNull String getName() {
        return this.name;
    }

    public @NotNull Status getStatus() {
        return this.status;
    }

    public @NotNull @Positive BigDecimal getPrice() {
        return this.price;
    }

    public @NotNull @PositiveOrZero int getStockQuantity() {
        return this.stockQuantity;
    }

    public @NotNull String getImageUrl() {
        return this.imageUrl;
    }

    public @NotNull String getDescription() {
        return this.description;
    }

    public Subcategory getSubcategory() {
        return this.subcategory;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setName(@NotNull String name) {
        this.name = name;
    }

    public void setStatus(@NotNull Status status) {
        this.status = status;
    }

    public void setPrice(@NotNull @Positive BigDecimal price) {
        this.price = price;
    }

    public void setStockQuantity(@NotNull @PositiveOrZero int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public void setImageUrl(@NotNull String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setDescription(@NotNull String description) {
        this.description = description;
    }

    public void setSubcategory(Subcategory subcategory) {
        this.subcategory = subcategory;
    }
}
