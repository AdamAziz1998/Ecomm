package com.azizONeill.product.model;


import com.azizONeill.product.model.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;
import java.util.List;
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
    @Positive
    @Column(name = "price", precision = 10)
    private double displayPrice;

    @NotNull
    @Column(name = "imageUrl")
    private String imageUrl;

    @NotNull
    @Column(name = "description")
    private String description;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "PRODUCT_ID")
    private List<ProductVariant> productVariants;

    public Product(UUID id, String name, double displayPrice, String imageUrl, String description, List<ProductVariant> productVariants) {
        this.id = id;
        this.name = name;
        this.displayPrice = displayPrice;
        this.imageUrl = imageUrl;
        this.description = description;
        this.productVariants = productVariants;
    }

    public Product() {
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDisplayPrice() {
        return displayPrice;
    }

    public void setDisplayPrice(double displayPrice) {
        this.displayPrice = displayPrice;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ProductVariant> getProductVariants() {
        return productVariants;
    }

    public void setProductVariants(List<ProductVariant> productVariants) {
        this.productVariants = productVariants;
    }
}

