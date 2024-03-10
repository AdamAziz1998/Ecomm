package com.azizONeill.product.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "subcategory")
public class Subcategory {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "subcategory_id", updatable = false, nullable = false)
    private UUID id;

    @NotNull
    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "SUBCATEGORY_ID")
    private List<Product> products;

    public Subcategory(UUID id, @NotNull String name, Category category, List<Product> products) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.products = products;
    }

    public Subcategory() {
    }

    public UUID getId() {
        return this.id;
    }

    public @NotNull String getName() {
        return this.name;
    }

    public Category getCategory() {
        return this.category;
    }

    public List<Product> getProducts() {
        return this.products;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setName(@NotNull String name) {
        this.name = name;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
